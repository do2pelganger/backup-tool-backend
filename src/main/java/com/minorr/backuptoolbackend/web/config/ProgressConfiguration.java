package com.minorr.backuptoolbackend.web.config;

public class ProgressConfiguration {
    public enum ACTION {
        UNINIT,
        INIT,
        COMPRESSION_AND_ENCRYPTION,
        FINISHING,
        FINISHED_OK,
        FINISHED_NOK, 
        RESTORING,
        MOVING, 
        MOVE_RESTORED_BACKUP,
    }
} 