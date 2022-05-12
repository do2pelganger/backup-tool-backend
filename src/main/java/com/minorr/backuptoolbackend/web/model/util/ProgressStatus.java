package com.minorr.backuptoolbackend.web.model.util;

import com.minorr.backuptoolbackend.web.config.ProgressConfiguration.ACTION;

import org.springframework.stereotype.Component;

@Component
public class ProgressStatus {
    private ACTION currentAction;
    private int percentDone;

    public ProgressStatus(){
        this.currentAction = ACTION.UNINIT;
        this.percentDone = 0;
    }

    public void reset() {
        this.currentAction = ACTION.UNINIT;
        this.percentDone = 0;
    }

    public void setCurrentAction(ACTION action){
        this.currentAction = action;
    }

    public void setPercentDone(int percentDone){
        this.percentDone = percentDone;
    }

    public ACTION getCurrentAction() {
        return currentAction;
    }

    public int getPercentDone(){
        return percentDone;
    }

    @Override
    public String toString() {
        return "ProgressStatus{" +
                "currentAction=" + currentAction +
                ", percentDone=" + percentDone +
                '}';
    }
}
