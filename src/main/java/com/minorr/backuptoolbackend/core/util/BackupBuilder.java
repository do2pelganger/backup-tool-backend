package com.minorr.backuptoolbackend.core.util;

import com.minorr.backuptoolbackend.core.model.Backup;

public class BackupBuilder {
    
    private String name;
    private String comment;
    private Long size;
    private Integer compressionMethod;
    private Integer encryptionMethod;
    private Boolean isCustomPassword;
    
    public void setName(String name){
        this.name = name;
    }
    public void setComment(String comment){
        this.comment = comment;
    }
    public void setSize(long size){
        this.size = size;
    }
    public void setCompressionMethod(Integer compressionMethod){
        this.compressionMethod = compressionMethod;
    }
    public void setEncryptionMethod(Integer encryptionMethod){
        this.encryptionMethod = encryptionMethod;
    }
    public void setIsCustomPassword(Boolean isCustomPassword){
        this.isCustomPassword = isCustomPassword;
    }

    public void reset(){
        this.name = null;
        this.comment = null;
        this.size = null;
        this.compressionMethod = null;
        this.encryptionMethod = null;
        this.isCustomPassword = null;
    }

    public Backup build(){
        return new Backup(name, size, comment, compressionMethod, encryptionMethod, isCustomPassword);
    }
}
