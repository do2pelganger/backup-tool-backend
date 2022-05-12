package com.minorr.backuptoolbackend.web.controller;

import javax.validation.Valid;

import com.minorr.backuptoolbackend.core.controller.BackupController;
import com.minorr.backuptoolbackend.core.model.Backup;
import com.minorr.backuptoolbackend.core.repository.BackupRepository;
import com.minorr.backuptoolbackend.web.config.Configuration;
import com.minorr.backuptoolbackend.web.config.MessageTemplates;
import com.minorr.backuptoolbackend.web.config.SocketEndpointConfiguration;
import com.minorr.backuptoolbackend.web.config.ProgressConfiguration.ACTION;
import com.minorr.backuptoolbackend.web.model.request.BackupAddRequest;
import com.minorr.backuptoolbackend.web.model.request.BackupRestoreRequest;
import com.minorr.backuptoolbackend.web.model.response.CollectionResponse;
import com.minorr.backuptoolbackend.web.model.util.ProgressStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/backups")
@CrossOrigin(origins = Configuration.FRONT_URL)
public class BackupRestController {
    public final long FIXED_RATE = 5000;


    private BackupRepository backupRepository;
    private BackupController backupController;

    private Boolean isOperationRunning;
    // socket utils
    private SimpMessagingTemplate template;
    private ProgressStatus progressStatus;
    
    @Autowired
    public BackupRestController(BackupRepository backupRepository, BackupController backupController, SimpMessagingTemplate template, ProgressStatus progressStatus){
        this.backupRepository = backupRepository;
        this.backupController = backupController;
        this.template = template;
        this.isOperationRunning = false;
        this.progressStatus = progressStatus;
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionResponse getAll(){
        CollectionResponse response = new CollectionResponse(HttpStatus.OK, MessageTemplates.NO_MSG);
        response.setData(this.backupRepository.getAll());
        return response;
    }

    @RequestMapping(value="/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public CollectionResponse createBackup(@Valid @RequestBody BackupAddRequest backupAddRequest){
        System.out.println("Name: " + backupAddRequest.getName() + " Comment: " + backupAddRequest.getComment() + " Pat: " + backupAddRequest.getPath());
        CollectionResponse response = null;
        if(!this.isOperationRunning){
            new Thread((() -> {
                this.isOperationRunning = true;
                this.progressStatus.setCurrentAction(ACTION.INIT);
                this.progressStatus.setPercentDone(5);
                this.sendProgress();

                Backup b = this.backupController.generateBackupModel(backupAddRequest.getName(), backupAddRequest.getComment(), backupAddRequest.getPath());
                this.sendProgress();

        
                try{
                    this.progressStatus.setCurrentAction(ACTION.COMPRESSION_AND_ENCRYPTION);
                    this.progressStatus.setPercentDone(25);
                    this.sendProgress();
                    this.backupController.compressAndEncrypt(b);
                    this.progressStatus.setPercentDone(65);
                    this.sendProgress();

                    this.progressStatus.setCurrentAction(ACTION.FINISHING);
                    this.progressStatus.setPercentDone(75);
                    this.sendProgress();
                    this.backupController.preFinalizeBackupCreation(b);
    
                    this.progressStatus.setPercentDone(85);
                    this.sendProgress();
                    this.backupController.finalizeBackupCreation(b);

                    this.progressStatus.setPercentDone(100);
                    this.progressStatus.setCurrentAction(ACTION.FINISHED_OK);
                    this.sendProgress();
                    this.isOperationRunning = false;

                }catch(Exception e){
                    this.backupController.clearStorageFolderFrom(b.getId().toString()); //delete unfinished backup from the storage folder
                    e.printStackTrace();
                }
    
            })).start();
            response = new CollectionResponse(HttpStatus.OK, MessageTemplates.BACKUP_CREATION_INIT_COMPLETE);
        }else{
            response = new CollectionResponse(HttpStatus.METHOD_NOT_ALLOWED, MessageTemplates.OPERATION_IN_PROGRESS);
        }
        return response;
    }

    @RequestMapping(value="/restore", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public CollectionResponse restoreBackup(@Valid @RequestBody BackupRestoreRequest backupRestoreRequest){
        System.out.println("Restoring backup: " + backupRestoreRequest.getBackupId());
        CollectionResponse response = null;
        if(!this.isOperationRunning){
            new Thread((() -> {
                this.isOperationRunning = true;
                this.progressStatus.setCurrentAction(ACTION.INIT);
                this.progressStatus.setPercentDone(25);
                this.sendProgress();
        
                try{
                    this.progressStatus.setCurrentAction(ACTION.RESTORING);
                    this.sendProgress();

                    this.backupController.restoreBackup(backupRestoreRequest.getBackupId());

                    this.progressStatus.setCurrentAction(ACTION.MOVE_RESTORED_BACKUP);
                    this.progressStatus.setPercentDone(75);
                    this.sendProgress();
                    this.backupController.moveRestoredBackup(backupRestoreRequest.getBackupId());


                    
                    this.progressStatus.setCurrentAction(ACTION.FINISHED_OK);
                    this.sendProgress();

                    this.isOperationRunning = false;

                }catch(Exception e){
                    e.printStackTrace();
                }
    
            })).start();
            response = new CollectionResponse(HttpStatus.OK, MessageTemplates.BACKUP_RESTORE_INIT_COMPLETE);
        }else{
            response = new CollectionResponse(HttpStatus.METHOD_NOT_ALLOWED, MessageTemplates.OPERATION_IN_PROGRESS);
        }

        return response;        
        
    }
    public void sendProgress() {
        if(this.progressStatus != null){
            System.out.println("Sending progress: " + this.progressStatus);
            this.template.convertAndSend(SocketEndpointConfiguration.PROGRESS_ENDPOINT, this.progressStatus);
        }     
    }
}
