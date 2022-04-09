package com.minorr.backuptoolbackend.core.controller;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.minorr.backuptoolbackend.core.config.StorageConfiguration;
import com.minorr.backuptoolbackend.core.model.Backup;
import com.minorr.backuptoolbackend.core.repository.BackupRepository;
import com.minorr.backuptoolbackend.core.util.BackupBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @TODO
 * implement dynamic encryption and compression methods
 */
@Controller
public class BackupController {
    
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
    
    private Backup generateBackup(String name, String comment, String path){
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
    public Boolean createBackup(String name, String comment, String path){
        Backup b = this.generateBackup(name, comment, path);

        try {
            
            this.compressAndEncrypt(b);
            System.out.println("Size: " + this.calculateBackupSize(b));
            b.setSize(this.calculateBackupSize(b));
            this.backupRepository.add(b);
            return true;
        } catch (Exception e) {
            this.clearStorageFolderFrom(b.getId().toString()); //delete unfinished backup from the storage folder
            e.printStackTrace();
            return false;
        }
    }
    public Long calculateBackupSize(Backup b) throws IOException{
        Path path = Paths.get(this.settingsController.getStorageFolder() + b.getId().toString() + StorageConfiguration.ENCRYPTED_EXT);
        Long size = Files.size(path);
        return size;

    }
    public Boolean restoreBackup(String id){
        try {
            
            Backup b = this.backupRepository.getById(id);
            if(b != null){
                this.zipController.unzip(settingsController.getStorageFolder() + id + StorageConfiguration.ENCRYPTED_EXT, b.getPath());
                return true;
            }else{
                throw new Exception("Backup with id " + id + " not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void compressAndEncrypt(Backup b){
        /**
         * @TODO
         * probably should change return type to boolean
         */
        this.zipController.enableEncryption(this.settingsController.getMasterPasswordHash());
        this.zipController.zip(b.getPath(), settingsController.getStorageFolder() + b.getId().toString() + StorageConfiguration.ENCRYPTED_EXT);
    }
    
    private Boolean clearStorageFolderFrom(String backupId){
        return this.fileManagerController.deleteFilesWithPrefix(StorageConfiguration.STORAGE, backupId);
    }

}
