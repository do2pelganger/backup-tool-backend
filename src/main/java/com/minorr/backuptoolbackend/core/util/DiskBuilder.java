package com.minorr.backuptoolbackend.core.util;

import java.time.LocalDateTime;
import com.minorr.backuptoolbackend.core.model.Disk;

public class DiskBuilder {
    
    private String label;
    private Long used;
    private Long free;
    private LocalDateTime dateTimeLastSnap;
    private String type;
    private Boolean isCompressed;
    private String fileSystem;
    private String serialNumber;
    
    public void setLabel(String label){
        this.label = label;
    }
    public void setUsed(Long used){
        this.used = used;
    }
    public void setFree(Long free){
        this.free = free;
    }
    public void setDateTimeLastSnap(LocalDateTime dateTimeLastSnap){
        this.dateTimeLastSnap = dateTimeLastSnap;
    }
    public void setType(String type){
        this.type = type;
    }
    public void setIsCompressed(Boolean isCompressed){
        this.isCompressed = isCompressed;
    }
    public void setFileSysString(String fileSystem){
        this.fileSystem = fileSystem;
    }
    public void setSerialNumber(String serialNumber){
        this.serialNumber = serialNumber;
    } 

    public Disk build(){
        return new Disk(label, used, free, dateTimeLastSnap, type, isCompressed, fileSystem, serialNumber);
    }
}
