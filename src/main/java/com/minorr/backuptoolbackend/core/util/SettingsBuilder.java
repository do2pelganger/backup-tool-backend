package com.minorr.backuptoolbackend.core.util;

import com.minorr.backuptoolbackend.core.model.Settings;

public class SettingsBuilder {
    
    private Integer encryptionMethod;
    private Integer compressionMethod;
    private String masterPassword;
    private String storageFolder;
    private Boolean isEncryptionEnabled;
    private Boolean isCompressionEnabled;
    private Boolean isMasterPasswordEnabled;

    public void setEncryptionMethod(Integer encryptionMethod) {
        this.encryptionMethod = encryptionMethod;
    }
    public void setCompressionMethod(Integer compressionMethod) {
        this.compressionMethod = compressionMethod;
    }   
    public void setMasterPassword(String masterPassword) {
        this.masterPassword = masterPassword;
    }
    public void setStorageFolder(String storageFolder) {
        this.storageFolder = storageFolder;
    }
    public void setIsEncryptionEnabled(Boolean isEncryptionEnabled) {
        this.isEncryptionEnabled = isEncryptionEnabled;
    }
    public void setIsCompressionEnabled(Boolean isCompressionEnabled) {
        this.isCompressionEnabled = isCompressionEnabled;
    }
    public void setIsMasterPasswordEnabled(Boolean isMasterPasswordEnabled) {
        this.isMasterPasswordEnabled = isMasterPasswordEnabled;
    }

    public SettingsBuilder(){
        this.encryptionMethod = null;
        this.compressionMethod = null;
        this.masterPassword = null;
        this.storageFolder = null;
    }

    public void reset(){
        this.encryptionMethod = null;
        this.compressionMethod = null;
        this.masterPassword = null;
        this.storageFolder = null;
        this.isEncryptionEnabled = null;
        this.isCompressionEnabled = null;
        this.isMasterPasswordEnabled = null;
    }
    public Settings build(){
        return new Settings(this.encryptionMethod, this.compressionMethod, this.masterPassword, this.storageFolder, this.isEncryptionEnabled, this.isCompressionEnabled, this.isMasterPasswordEnabled);    
    }
}
