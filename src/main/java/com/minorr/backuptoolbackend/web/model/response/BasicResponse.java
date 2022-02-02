package com.minorr.backuptoolbackend.web.model.response;

import java.util.Collection;

import org.springframework.http.HttpStatus;

public class BasicResponse {
    private HttpStatus status;
    private String message;
    private Collection data;

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

    public Collection getData() {
        return data;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
    
    public void setData(Collection data) {
        this.data = data;
    }
    
}
