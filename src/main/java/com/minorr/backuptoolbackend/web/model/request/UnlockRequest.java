package com.minorr.backuptoolbackend.web.model.request;

import javax.validation.constraints.NotNull;

public class UnlockRequest {
    @NotNull
    private String password;

    public UnlockRequest() {}

    public UnlockRequest(String password) {
        this.password = password;
    }
    public String getPassword() {
        return this.password;
    }
}
