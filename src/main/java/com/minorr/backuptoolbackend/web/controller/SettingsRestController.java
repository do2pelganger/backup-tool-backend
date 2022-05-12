package com.minorr.backuptoolbackend.web.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

import com.minorr.backuptoolbackend.core.controller.SettingsController;
import com.minorr.backuptoolbackend.core.service.FileSystemInfoService;
import com.minorr.backuptoolbackend.web.config.MessageTemplates;
import com.minorr.backuptoolbackend.web.model.response.CollectionResponse;
import com.minorr.backuptoolbackend.web.model.response.SettingsResponse;
import com.minorr.backuptoolbackend.web.config.Configuration;

@RestController
@RequestMapping("/settings")
@CrossOrigin(origins = Configuration.FRONT_URL)
public class SettingsRestController {

    private SettingsController sc;
    
    @Autowired
    public SettingsRestController(SettingsController settingsController) {
        this.sc = settingsController;
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public SettingsResponse getDeviceStat(){
        SettingsResponse response = new SettingsResponse(HttpStatus.OK, MessageTemplates.NO_MSG);
        response.setSettings(sc.getSettings());

        return response;
    }
    
}
