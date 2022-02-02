package com.minorr.backuptoolbackend.core.controller;

import org.springframework.stereotype.Controller;

@Controller
public class LockController {
    public final String password = "test";

    public LockController(){
    }
    
    public Boolean isCorrect(String password){
        return this.password.equals(password);
    }
    
}
