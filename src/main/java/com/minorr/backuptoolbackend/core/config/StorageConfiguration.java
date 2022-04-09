package com.minorr.backuptoolbackend.core.config;

public class StorageConfiguration {
    public static final String FILE_SEPARATOR = getFileSeparator();

    public static final String HOME = System.getProperty("user.dir") + FILE_SEPARATOR;
    public static final String STORAGE = HOME + "storage" + FILE_SEPARATOR;

    public static final String ENCRYPTED_EXT = ".wnbck";
    public static final String COMPRESSED_EXT = ".zip";
    /**
     * @TODO
     * DELETE AFTER TESTING
     */
    public static final String DEBUG_PASSWORD_HASH = "9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08";

    private static String getFileSeparator(){
       return System.getProperty("file.separator");
    }

}
