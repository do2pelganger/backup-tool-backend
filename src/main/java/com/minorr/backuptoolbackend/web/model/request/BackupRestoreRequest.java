package com.minorr.backuptoolbackend.web.model.request;

import javax.validation.constraints.NotNull;

public class BackupRestoreRequest {
    @NotNull
    private String backupId;

    public BackupRestoreRequest() {}

    public BackupRestoreRequest(String backupId) {
        this.backupId = backupId;
    }
    public String getBackupId() {
        return this.backupId;
    }
}
