package com.minorr.backuptoolbackend.web.model.request;

public class UnlockRequest {
    private String password;

    public UnlockRequest() {}

    public UnlockRequest(String password) {
        this.password = password;
    }
    public String getPassword() {
        return this.password;
    }
}
