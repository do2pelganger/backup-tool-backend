package com.minorr.backuptoolbackend.core.model;

public class Settings {
    private String encryptionMethod;
    private String compressionMethod;
    private String masterPasswordHash;
    private String storageFolder;

    public Settings(String encryptionMethod, String compressionMethod, String masterPasswordHash, String storageFolder){
        this.encryptionMethod = encryptionMethod;
        this.compressionMethod = compressionMethod;
        this.masterPasswordHash = masterPasswordHash;
        this.storageFolder = storageFolder;
    }

    public String getEncryptionMethod(){
        return encryptionMethod;
    }
    public String getCompressionMethod(){
        return compressionMethod;
    }
    public String getMasterPasswordHash(){
        return masterPasswordHash;
    }
    public String getStorageFolder(){
        return storageFolder;
    }
}
