package com.minorr.backuptoolbackend.web.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import com.minorr.backuptoolbackend.core.service.FileSystemInfoService;
import com.minorr.backuptoolbackend.web.config.MessageTemplates;
import com.minorr.backuptoolbackend.web.model.response.BasicResponse;
import com.minorr.backuptoolbackend.web.model.response.CollectionResponse;
import com.minorr.backuptoolbackend.web.config.Configuration;

@RestController
@RequestMapping("/info")
@CrossOrigin(origins = Configuration.FRONT_URL)
public class DeviceInfoRestController {
    @RequestMapping(value = "/storage", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionResponse getDeviceStat(){
        FileSystemInfoService fsis = new FileSystemInfoService();
        CollectionResponse response = new CollectionResponse(HttpStatus.OK, MessageTemplates.NO_MSG);
        response.setData(fsis.getDisksInfo());

        return response;
    }
    
}
