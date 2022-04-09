package com.minorr.backuptoolbackend.core.repository;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.minorr.backuptoolbackend.core.config.DatabaseConfiguration;
import com.minorr.backuptoolbackend.core.config.StorageConfiguration;
import com.minorr.backuptoolbackend.core.util.DBHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SettingsRepository {
    private DBHelper dbHelper;
    private String tableName;

    @Autowired
    public SettingsRepository(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
        this.tableName = DatabaseConfiguration.SETTINGS_TABLENAME;
    }

    public String getMasterPasswordHash(){
        String query = "SELECT master_password FROM " + this.tableName;
        String masterPassHash = null;
        try {
            Statement stmt = this.dbHelper.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (!rs.isBeforeFirst() ) {    
                return StorageConfiguration.DEBUG_PASSWORD_HASH;
            } 
            while (rs.next()) {
                masterPassHash = rs.getString(1);
            }
            return masterPassHash;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public String getStorageFolder(){
        String query = "SELECT storage_folder FROM " + this.tableName;
        String storageFolderPath = null;
        try {
            Statement stmt = this.dbHelper.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(query);
           
            while (rs.next()) {
                storageFolderPath = rs.getString(1);
            }
            return storageFolderPath;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
