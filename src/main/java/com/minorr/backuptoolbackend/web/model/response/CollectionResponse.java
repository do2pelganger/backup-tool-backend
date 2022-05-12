package com.minorr.backuptoolbackend.web.model.response;

import java.util.Collection;

import org.springframework.http.HttpStatus;

public class CollectionResponse extends BasicResponse {
    private Collection data;

    public CollectionResponse(HttpStatus status, String message){
        super(status, message);
        this.data = null;
    }

    public Collection getData() {
        return data;
    }

    public void setData(Collection data) {
        this.data = data;
    }
    
}
