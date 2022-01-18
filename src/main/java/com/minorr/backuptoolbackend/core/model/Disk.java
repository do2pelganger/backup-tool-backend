package com.minorr.backuptoolbackend.core.model;

import java.time.LocalDateTime;

/**
 *   { "disk":"c", "used":"160800", "free":"474200", "lastSnap":"23.08.2021 14:23", "type":"Local fixed disk", "compressed":"no", "fileSystem":"NTFS","serialNumber":"702B4546" }
 */
public class Disk {
    
    private String label;
    private Long used;
    private Long free;
    private LocalDateTime dateTimeLastSnap;
    private String type;
    private Boolean isCompressed;
    private String fileSystem;
    private String serialNumber;

    public Disk(String label, Long used, Long free, LocalDateTime dateTimeLastSnap, String type, Boolean isCompressed, String fileSystem, String serialNumber){
        this.label = label;
        this.used = used;
        this.free = free;
        this.dateTimeLastSnap = dateTimeLastSnap;
        this.type = type;
        this.isCompressed = isCompressed;
        this.fileSystem = fileSystem;
        this.serialNumber = serialNumber;
    }

    public String getLabel(){
        return label;
    }
    public Long getUsed(){
        return used;
    }
    public Long getFree(){
        return free;
    }
    public LocalDateTime getDateTimeLastSnap(){
        return dateTimeLastSnap;
    }
    public String getType(){
        return type;
    }
    public Boolean getIsCompressed(){
        return isCompressed;
    }
    public String getFileSysString(){
        return fileSystem;
    }
    public String getSerialNumber(){
        return serialNumber;
    }

}
