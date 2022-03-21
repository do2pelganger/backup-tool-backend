package com.minorr.backuptoolbackend.core.model;
import java.time.Instant;
import java.util.UUID;

public class Backup {

    private UUID id;
    private String name;
    private String comment;
    private Long created;
    private Long size;
    private Integer compressionMethod;
    private Integer encryptionMethod;
    private Boolean isCustomPassword;

    public Backup(String name, Long size, String comment, Integer compressionMethod, Integer encryptionMethod, Boolean isCustomPassword){
        this.id = UUID.randomUUID();
        this.name = name;
        this.comment = comment;
        this.created = Instant.now().getEpochSecond();
        this.size = size;
        this.compressionMethod = compressionMethod;
        this.encryptionMethod = encryptionMethod;
        this.isCustomPassword = isCustomPassword;
    }

    public UUID getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getComment(){
        return comment;
    }
    public Long getCreated(){
        return created;
    }
    public Long getSize(){
        return size;
    }
    public Integer getCompressionMethod(){
        return compressionMethod;
    }
    public Integer getEncryptionMethod(){
        return encryptionMethod;
    }
    public Boolean getIsCustomPassword(){
        return isCustomPassword;
    }

    
}
