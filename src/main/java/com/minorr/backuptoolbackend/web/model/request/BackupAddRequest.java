package com.minorr.backuptoolbackend.web.model.request;

import javax.validation.constraints.NotNull;

public class BackupAddRequest {
    @NotNull
    private String name;
    @NotNull
    private String comment;
    @NotNull
    private String path;

    public BackupAddRequest() {}

    public BackupAddRequest(String name, String comment, String path) {
        this.name = name;
        this.comment = comment;
        this.path = path;
    }
    public String getName() {
        return this.name;
    }
    public String getComment() {
        return this.comment;
    }
    public String getPath() {
        return this.path;
    }
}
