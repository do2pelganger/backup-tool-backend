package com.minorr.backuptoolbackend.core.model;

import java.time.LocalDateTime;
/**
 *   { "disk":"c", "used":"160800", "free":"474200", "lastSnap":"23.08.2021 14:23", "type":"Local fixed disk", "compressed":"no", "fileSystem":"NTFS","serialNumber":"702B4546" }
 */
public class Disk {
    
    private String label;
    private String mount;
    private Long used;
    private Long free;
    private LocalDateTime dateTimeLastSnap;
    private String type;
    private String fileSystem;
    private String model;

    public Disk(String label, String mount, Long used, Long free, LocalDateTime dateTimeLastSnap, String type, String fileSystem, String model){
        this.label = label;
        this.mount = mount;
        this.used = used;
        this.free = free;
        this.dateTimeLastSnap = dateTimeLastSnap;
        this.type = type;
        this.fileSystem = fileSystem;
        this.model = model;
    }

    public String getLabel(){
        return label;
    }
    public String getMount(){
        return mount;
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
    public String getFileSystem(){
        return fileSystem;
    }
    public String getModel(){
        return model;
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        result.append("{");
        result.append("label:" + label);
        result.append(",used: " + used);
        result.append(",free: " + free);
        // result.append(",dateTimeLastSnap: " + dateTimeLastSnap.toString());
        result.append(",type: " + type);
        result.append(",fileSystem: " + fileSystem);
        result.append(",model: " + model);
        result.append("}");

        return result.toString();
    }
}
