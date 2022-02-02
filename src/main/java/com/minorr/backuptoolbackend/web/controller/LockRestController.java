package com.minorr.backuptoolbackend.web.controller;

import java.util.Map;

import com.minorr.backuptoolbackend.core.controller.LockController;
import com.minorr.backuptoolbackend.web.config.Configuration;
import com.minorr.backuptoolbackend.web.config.MessageTemplates;
import com.minorr.backuptoolbackend.web.model.request.UnlockRequest;
import com.minorr.backuptoolbackend.web.model.response.BasicResponse;

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
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public BasicResponse unlock(@RequestBody UnlockRequest unlockRequest){
        LockController lockController = new LockController();
        System.out.println(unlockRequest.getPassword());
        if(lockController.isCorrect(unlockRequest.getPassword())){
            BasicResponse response = new BasicResponse(HttpStatus.OK, MessageTemplates.NO_MSG);
            return response;
        }
        
        throw new IncorrectPasswordException();
        
        
    }
    @ResponseStatus(value=HttpStatus.FORBIDDEN, reason="Incorrect password")  // 404
    public class IncorrectPasswordException extends RuntimeException {}
}
