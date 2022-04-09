package com.minorr.backuptoolbackend.core.config;

import java.util.Arrays;
import java.util.List;

public class DatabaseConfiguration {
    public static final String DB_NAME = "system_data.db";

    // table names
    public static final String BACKUPS_TABLENAME = "backups";
    public static final String SETTINGS_TABLENAME = "settings";
    public static final String ENCRYPTION_METHODS_TABLENAME = "encryption_methods";
    public static final String COMPRESSION_METHODS_TABLENAME = "compression_methods";

    private static final String DDL_BACKUPS = "CREATE TABLE IF NOT EXISTS " + BACKUPS_TABLENAME + "(" 
        + "id	TEXT NOT NULL UNIQUE,"
        + "name	TEXT NOT NULL,"
        + "comment	TEXT,"
        + "path	TEXT NOT NULL,"
        + "created	INTEGER NOT NULL,"
        + "size	REAL,"
        + "compression_method	INTEGER NOT NULL,"
        + "encryption_method	INTEGER NOT NULL,"
        + "is_custom_password	INTEGER NOT NULL,"
        + "PRIMARY KEY(id)"
    + ");";
    
    private static final String DDL_SETTINGS = "CREATE TABLE IF NOT EXISTS " + SETTINGS_TABLENAME + "("
        + "encryption_method	INTEGER,"
        + "compression_method	INTEGER,"
        + "master_password	TEXT,"
        + "storage_folder	TEXT NOT NULL,"
        + "is_encryption_enabled	INTEGER NOT NULL,"
        + "is_compression_enabled	INTEGER NOT NULL,"
        + "is_master_enabled	INTEGER NOT NULL"
    + ");";

    private static final String DDL_ENCRYPION_METHODS = "CREATE TABLE IF NOT EXISTS " + ENCRYPTION_METHODS_TABLENAME + "("
        + "id	INTEGER NOT NULL UNIQUE,"
        + "name	TEXT NOT NULL,"
        + "PRIMARY KEY(id AUTOINCREMENT)"
    + ");";

    private static final String DDL_COMPRESSION_METHODS = "CREATE TABLE IF NOT EXISTS " + COMPRESSION_METHODS_TABLENAME + "( "
        + "id	INTEGER NOT NULL UNIQUE,"
        + "name	TEXT NOT NULL,"
        + "PRIMARY KEY(id AUTOINCREMENT)"
    + ");";

    public static final List<String> DDL_CREATE = Arrays.asList(DDL_BACKUPS, DDL_SETTINGS, DDL_ENCRYPION_METHODS, DDL_COMPRESSION_METHODS);
}
