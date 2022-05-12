package com.minorr.backuptoolbackend.core.controller;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.commons.io.FileUtils;

import com.minorr.backuptoolbackend.core.config.StorageConfiguration;
import com.minorr.backuptoolbackend.core.model.Backup;
import com.minorr.backuptoolbackend.core.repository.BackupRepository;
import com.minorr.backuptoolbackend.core.util.BackupBuilder;
import com.minorr.backuptoolbackend.web.model.util.ProgressStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @TODO
 * implement dynamic encryption and compression methods
 */
@Controller
public class BackupController{
    
    private BackupRepository backupRepository;
    private FileManagerController fileManagerController;
    private ZipController zipController;
    private SettingsController settingsController;

    @Autowired
    public BackupController(BackupRepository backupRepository, FileManagerController fileManagerController, ZipController zipController, SettingsController settingsController) {
        this.backupRepository = backupRepository;
        this.fileManagerController = fileManagerController;
        this.zipController = zipController;
        this.settingsController = settingsController;
    }
    
    public Backup generateBackupModel(String name, String comment, String path){
        BackupBuilder bb = new BackupBuilder();

        bb.generateUUID();
        bb.setName(name);
        bb.setComment(comment);
        bb.setPath(path);
        // get compression & encryption methods from SettingsController
        bb.setCompressionMethod(1);
        bb.setEncryptionMethod(2);
        bb.setIsCustomPassword(false);
        return bb.build();
    }


    public void preFinalizeBackupCreation(Backup b) throws IOException{
        // to megabytes
        b.setSize(this.calculateBackupSize(b));
    }
    
    public void finalizeBackupCreation(Backup b){
        System.out.println("finalizeBackupCreation " + b);
        this.backupRepository.add(b);
    }
   
    public Double calculateBackupSize(Backup b) throws IOException{
        Path path = Paths.get(this.settingsController.getStorageFolder() + b.getId().toString() + StorageConfiguration.ENCRYPTED_EXT);
        Long sizeBytes = Files.size(path);
        System.out.println("Size before: " + sizeBytes);
        return Double.valueOf(sizeBytes / (1024 * 1024));

    }
    public Boolean restoreBackup(String id){
        try {
            
            Backup b = this.backupRepository.getById(id);
            this.zipController.enableEncryption(this.settingsController.getMasterPasswordHash());

            if(b != null){
                this.zipController.unzip(settingsController.getStorageFolder() + id + StorageConfiguration.ENCRYPTED_EXT, settingsController.getStorageFolder() + id);
                return true;
            }else{
                throw new Exception("Backup with id " + id + " not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public Boolean moveRestoredBackup(String id){
        try {
            Backup b = this.backupRepository.getById(id);
            File src = new File(settingsController.getStorageFolder() + b.getId().toString());
            File dest = new File(b.getPath());

            for(File child: src.listFiles()){
                if(child.isDirectory()){
                    FileUtils.moveDirectoryToDirectory(child, dest, false);
                }else{
                    FileUtils.moveFileToDirectory(child, dest, false);
                }
            }
            // FileUtils.moveDirectoryToDirectory(src, dest, false);
            return true;
        }catch (IOException e){
            e.printStackTrace();
            throw new UncheckedIOException(e);
        }
    }
    public void compressAndEncrypt(Backup b){
        /**
         * @TODO
         * probably should change return type to boolean
         */
        

        this.zipController.enableEncryption(this.settingsController.getMasterPasswordHash());
        this.zipController.zip(b.getPath(), settingsController.getStorageFolder() + b.getId().toString() + StorageConfiguration.ENCRYPTED_EXT);
    }
    
    public Boolean clearStorageFolderFrom(String backupId){
        return this.fileManagerController.deleteFilesWithPrefix(StorageConfiguration.STORAGE, backupId);
    }

}
