package com.minorr.backuptoolbackend.core.model;
import java.time.LocalDateTime;
import java.util.UUID;

public class Snapshot {

    private UUID id;
    private String name;
    private LocalDateTime dateTimeCreated;
    private long size;
    private String comment;

    public Snapshot(String name, long size, String comment){
        this.id = UUID.randomUUID();
        this.name = name;
        this.dateTimeCreated = LocalDateTime.now();
        this.size = size;
        this.comment = comment;
    }

    public UUID getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public LocalDateTime getDateTimeCreated(){
        return dateTimeCreated;
    }
    public long getSize(){
        return size;
    }
    public String getComment(){
        return comment;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setSize(long size){
        this.size = size;
    }
    public void setComment(String comment){
        this.comment = comment;
    }
}
