package com.minorr.backuptoolbackend.core.model;

public class Settings {
    private Integer encryptionMethod;
    private Integer compressionMethod;
    private String masterPassword;
    private String storageFolder;
    private Boolean isEncryptionEnabled;
    private Boolean isCompressionEnabled;
    private Boolean isMasterPasswordEnabled;

    public Integer getEncryptionMethod() {
        return encryptionMethod;
    }
    public Integer getCompressionMethod() {
        return compressionMethod;
    }
    public String getMasterPassword() {
        return masterPassword;
    }
    public String getStorageFolder() {
        return storageFolder;
    }
    public Boolean getIsEncryptionEnabled() {
        return isEncryptionEnabled;
    }
    public Boolean getIsCompressionEnabled() {
        return isCompressionEnabled;
    }
    public Boolean getIsMasterPasswordEnabled() {
        return isMasterPasswordEnabled;
    }
    

    public Settings(Integer encryptionMethod, Integer compressionMethod, String masterPassword, String storageFolder, Boolean isEncryptionEnabled, Boolean isCompressionEnabled, Boolean isMasterPasswordEnabled){
        this.encryptionMethod = encryptionMethod;
        this.compressionMethod = compressionMethod;
        this.masterPassword = masterPassword;
        this.storageFolder = storageFolder;
        this.isEncryptionEnabled = isEncryptionEnabled;
        this.isCompressionEnabled = isCompressionEnabled;
        this.isMasterPasswordEnabled = isMasterPasswordEnabled;
    }
}
