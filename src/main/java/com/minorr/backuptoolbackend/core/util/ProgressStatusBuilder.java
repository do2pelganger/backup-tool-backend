package com.minorr.backuptoolbackend.core.util;

import com.minorr.backuptoolbackend.core.model.ProgressStatus;

public class ProgressStatusBuilder {
    private String currentFileName;
    private int percentDone;

    public void setCurrentFileName(String currentFileName) {
        this.currentFileName = currentFileName;
    }
    public void setPercentDone(int percentDone){
        this.percentDone = percentDone;
    }

    public void reset(){
        this.currentFileName = null;
        this.percentDone = 0;
    }

    public ProgressStatus build(){
        return new ProgressStatus(this.currentFileName, this.percentDone);
    }
}
