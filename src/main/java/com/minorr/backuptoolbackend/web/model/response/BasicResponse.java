package com.minorr.backuptoolbackend.web.model.response;

import java.util.Collection;

import org.springframework.http.HttpStatus;

public abstract class BasicResponse {
    private HttpStatus status;
    private String message;

    public BasicResponse(HttpStatus status, String message){
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }
    public String getMessage() {
        return message;
    }
    public void setStatus(HttpStatus status) {
        this.status = status;
    }
    
}
