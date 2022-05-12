package com.minorr.backuptoolbackend.core.util;

import java.time.LocalDateTime;
import com.minorr.backuptoolbackend.core.model.Disk;

public class DiskBuilder {
    
    private String label;
    private String mount;
    private Long used;
    private Long free;
    private LocalDateTime dateTimeLastSnap;
    private String type;
    private String fileSystem;
    private String model;
    
    public void setLabel(String label) {
        this.label = label;
    }
    public void setMount(String mount) {
        this.mount = mount;
    }
    public void setUsed(Long used) {
        this.used = used;
    }
    public void setFree(Long free) {
        this.free = free;
    }
    public void setDateTimeLastSnap(LocalDateTime dateTimeLastSnap) {
        this.dateTimeLastSnap = dateTimeLastSnap;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setFileSystem(String fileSystem) {
        this.fileSystem = fileSystem;
    }
    public void setModel(String model) {
        this.model = model;
    }

    public void reset(){
        this.label = null;
        this.mount = null;
        this.used = null;
        this.free = null;
        this.dateTimeLastSnap = null;
        this.type = null;
        this.fileSystem = null;
        this.model = null;
    }
    public Disk build(){
        return new Disk(label, mount, used, free, dateTimeLastSnap, type, fileSystem, model);
    }
}
