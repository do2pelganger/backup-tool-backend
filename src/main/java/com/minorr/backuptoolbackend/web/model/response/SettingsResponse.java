package com.minorr.backuptoolbackend.web.model.response;


import com.minorr.backuptoolbackend.core.model.Settings;

import org.springframework.http.HttpStatus;

public class SettingsResponse extends BasicResponse {
    private Settings data;

    public SettingsResponse(HttpStatus status, String message){
        super(status, message);
        this.data = null;
    }

    public Settings getSettings() {
        return data;
    }
    
    public void setSettings(Settings data) {
        this.data = data;
    }
    
}
