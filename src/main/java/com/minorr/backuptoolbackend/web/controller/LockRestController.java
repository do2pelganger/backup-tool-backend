package com.minorr.backuptoolbackend.web.controller;

import java.util.Map;

import javax.validation.Valid;

import com.minorr.backuptoolbackend.core.controller.SettingsController;
import com.minorr.backuptoolbackend.web.config.Configuration;
import com.minorr.backuptoolbackend.web.config.MessageTemplates;
import com.minorr.backuptoolbackend.web.model.request.UnlockRequest;
import com.minorr.backuptoolbackend.web.model.response.BasicResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/unlock")
@CrossOrigin(origins = Configuration.FRONT_URL)
public class LockRestController {
    private SettingsController settingsController;

    @Autowired
    public LockRestController(SettingsController settingsController) {
        this.settingsController = settingsController;
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public BasicResponse unlock(@Valid @RequestBody UnlockRequest unlockRequest){
        System.out.println(unlockRequest.getPassword());
        if(this.settingsController.isMasterPassCorrect(unlockRequest.getPassword())){
            BasicResponse response = new BasicResponse(HttpStatus.OK, MessageTemplates.NO_MSG);
            return response;
        }
        
        throw new IncorrectPasswordException();
        
        
    }
    @ResponseStatus(value=HttpStatus.FORBIDDEN, reason="Incorrect password")  // 404
    public class IncorrectPasswordException extends RuntimeException {}
}
