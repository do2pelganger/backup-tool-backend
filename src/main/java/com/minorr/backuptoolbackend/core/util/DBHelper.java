package com.minorr.backuptoolbackend.core.util;

import java.sql.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.minorr.backuptoolbackend.core.config.DatabaseConfiguration;
import com.minorr.backuptoolbackend.core.config.StorageConfiguration;

@Component
@Scope("singleton")
public class DBHelper {  

    private Connection conn;

    public DBHelper(){
        try {
            this.createTables();
        } catch (Exception e) {
            System.out.println(e.getMessage());  
        }
    }

    private String getConnectionUrl(){
        return "jdbc:sqlite:" + StorageConfiguration.HOME + DatabaseConfiguration.DB_NAME;  
    }
    
    private Boolean createTables(){
        try {
            for(String ddl : DatabaseConfiguration.DDL_CREATE){
                PreparedStatement ps = this.getConnection().prepareStatement(ddl);
                ps.executeUpdate();
            }
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());  
            return false;
        }
    }
    public Boolean beginTransaction(){
        try {
            this.getConnection().setAutoCommit(false);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public Boolean commitTransaction(){
        try {
            this.getConnection().commit();
            this.getConnection().setAutoCommit(true);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public Boolean rollbackTransaction(){
        try {
            this.getConnection().rollback();
            this.getConnection().setAutoCommit(true);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public Connection getConnection() {  
        if(this.conn == null){
            try {  
                this.conn = DriverManager.getConnection(getConnectionUrl());  
            } catch (SQLException e) {  
                System.out.println(e.getMessage());  
                return null;
            }  
        }
        return this.conn;
    }  
}  