package com.minorr.backuptoolbackend.core.model;

public class Settings {
    private Integer encryptionMethod;
    private Integer compressionMethod;
    private String masterPassword;
    private String storageFolder;

    public Settings(Integer encryptionMethod, Integer compressionMethod, String masterPassword, String storageFolder){
        this.encryptionMethod = encryptionMethod;
        this.compressionMethod = compressionMethod;
        this.masterPassword = masterPassword;
        this.storageFolder = storageFolder;
    }

    public Integer getEncryptionMethod(){
        return encryptionMethod;
    }
    public Integer getCompressionMethod(){
        return compressionMethod;
    }
    public String getMasterPassword(){
        return masterPassword;
    }
    public String getStorageFolder(){
        return storageFolder;
    }
}
