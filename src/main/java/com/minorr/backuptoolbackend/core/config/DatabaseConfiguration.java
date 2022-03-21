package com.minorr.backuptoolbackend.core.config;

import java.util.Arrays;
import java.util.List;

public class DatabaseConfiguration {
    public static final String DB_NAME = "system_data";

    private static final String DDL_BACKUPS = "CREATE TABLE IF NOT EXISTS backups(" 
        + "id	TEXT NOT NULL UNIQUE,"
        + "name	TEXT NOT NULL,"
        + "comment	TEXT,"
        + "created	INTEGER NOT NULL,"
        + "size	REAL,"
        + "compression_method	INTEGER NOT NULL,"
        + "encryption_method	INTEGER NOT NULL,"
        + "is_custom_password	INTEGER NOT NULL,"
        + "PRIMARY KEY(id)"
    + ");";
    
    private static final String DDL_SETTINGS = "CREATE TABLE IF NOT EXISTS settings ("
        + "encryption_method	INTEGER NOT NULL,"
        + "compression_method	INTEGER NOT NULL,"
        + "master_password	TEXT"
    + ");";

    private static final String DDL_ENCRYPION_METHODS = "CREATE TABLE IF NOT EXISTS encryption_methods ("
        + "id	INTEGER NOT NULL UNIQUE,"
        + "name	TEXT NOT NULL,"
        + "PRIMARY KEY(id AUTOINCREMENT)"
    + ");";

    private static final String DDL_COMPRESSION_METHODS = "CREATE TABLE IF NOT EXISTS compression_methods ("
        + "id	INTEGER NOT NULL UNIQUE,"
        + "name	TEXT NOT NULL,"
        + "PRIMARY KEY(id AUTOINCREMENT)"
    + ");";

    public static final List<String> DDL_CREATE = Arrays.asList(DDL_BACKUPS, DDL_SETTINGS, DDL_ENCRYPION_METHODS, DDL_COMPRESSION_METHODS);
}
