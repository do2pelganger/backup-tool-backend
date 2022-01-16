package com.minorr.backuptoolbackend.web.model;

import java.util.Collection;

public class BasicResponse {
    private String status;
    private String msg;
    private Collection data;

    public BasicResponse(String status, String msg, Collection data){
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public Collection getData() {
        return data;
    }
    
}
