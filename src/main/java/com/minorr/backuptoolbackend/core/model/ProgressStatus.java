package com.minorr.backuptoolbackend.core.model;

public class ProgressStatus {
    private String currentFileName;
    private int percentDone;

    public ProgressStatus(String currentFileName, int percentDone){
        this.currentFileName = currentFileName;
        this.percentDone = percentDone;
    }
    
    public String getCurrentFileName() {
        return currentFileName;
    }
    public int getPercentDone(){
        return percentDone;
    }
}
