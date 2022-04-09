package com.minorr.backuptoolbackend.core.util;

import java.util.UUID;

import com.minorr.backuptoolbackend.core.model.Backup;

public class BackupBuilder {
    
    private UUID id;
    private String name;
    private String comment;
    private String path;
    private Long created;
    private Long size;
    private Integer compressionMethod;
    private Integer encryptionMethod;
    private Boolean isCustomPassword;

    public void setId(String id){
        this.id = UUID.fromString(id);
    }
    public void generateUUID(){
        this.id = UUID.randomUUID();
    }
    public void setName(String name){
        this.name = name;
    }
    public void setComment(String comment){
        this.comment = comment;
    }
    public void setPath(String path){
        this.path = path;
    }
    public void setCreated(Long created){
        this.created = created;
    }
    public void setSize(Long size){
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
        this.id = null;
        this.name = null;
        this.comment = null;
        this.path = null;
        this.created = null;
        this.size = null;
        this.compressionMethod = null;
        this.encryptionMethod = null;
        this.isCustomPassword = null;
    }

    public Backup build(){
        if(created == null){
            // new backup
            return new Backup(id, name, size, comment, path, compressionMethod, encryptionMethod, isCustomPassword);
        }
        // existing backup
        return new Backup(id, name, size, comment, path, created, compressionMethod, encryptionMethod, isCustomPassword);
    }
}
