package com.minorr.backuptoolbackend.web.controller;

import javax.validation.Valid;

import com.minorr.backuptoolbackend.core.controller.BackupController;
import com.minorr.backuptoolbackend.core.repository.BackupRepository;
import com.minorr.backuptoolbackend.web.config.Configuration;
import com.minorr.backuptoolbackend.web.config.MessageTemplates;
import com.minorr.backuptoolbackend.web.model.request.BackupAddRequest;
import com.minorr.backuptoolbackend.web.model.request.BackupRestoreRequest;
import com.minorr.backuptoolbackend.web.model.response.BasicResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/backups")
@CrossOrigin(origins = Configuration.FRONT_URL)
public class BackupRestController {

    private BackupRepository backupRepository;
    private BackupController backupController;

    @Autowired
    public BackupRestController(BackupRepository backupRepository, BackupController backupController){
        this.backupRepository = backupRepository;
        this.backupController = backupController;
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public BasicResponse getAll(){
        BasicResponse response = new BasicResponse(HttpStatus.OK, MessageTemplates.NO_MSG);
        response.setData(this.backupRepository.getAll());
        return response;
    }

    @RequestMapping(value="/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public BasicResponse createBackup(@Valid @RequestBody BackupAddRequest backupAddRequest){
        System.out.println("Name: " + backupAddRequest.getName() + " Comment: " + backupAddRequest.getComment() + " Path: " + backupAddRequest.getPath());
        if(backupController.createBackup(backupAddRequest.getName(), backupAddRequest.getComment(), backupAddRequest.getPath())){
            BasicResponse response = new BasicResponse(HttpStatus.OK, MessageTemplates.NO_MSG);
            return response;
        }
        return null;
        // throw new IncorrectPasswordException();
        
        
    }
    @RequestMapping(value="/restore", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public BasicResponse restoreBackup(@Valid @RequestBody BackupRestoreRequest backupRestoreRequest){
        System.out.println("Backup Id: " + backupRestoreRequest.getBackupId());
        if(backupController.restoreBackup(backupRestoreRequest.getBackupId())){
            BasicResponse response = new BasicResponse(HttpStatus.OK, MessageTemplates.NO_MSG);
            return response;
        }
        return null;
        // throw new IncorrectPasswordException();
        
        
    }
}
