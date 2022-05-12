package com.minorr.backuptoolbackend.core.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.minorr.backuptoolbackend.core.config.DatabaseConfiguration;
import com.minorr.backuptoolbackend.core.model.Backup;
import com.minorr.backuptoolbackend.core.util.BackupBuilder;
import com.minorr.backuptoolbackend.core.util.DBHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BackupRepository {

    private DBHelper dbHelper;
    private String tableName;

    @Autowired
    public BackupRepository(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
        this.tableName = DatabaseConfiguration.BACKUPS_TABLENAME;
    }

    public List<Backup> getAll(){
        String query = "SELECT * FROM " + this.tableName;
        try {
            Statement stmt = this.dbHelper.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(query);

            BackupBuilder bb = new BackupBuilder();
            ArrayList<Backup> backupList = new ArrayList<Backup>();
            
            while (rs.next()) {
                bb.reset();

                bb.setId(rs.getString(1));
                bb.setName(rs.getString(2));
                bb.setComment(rs.getString(3));
                bb.setPath(rs.getString(4));
                bb.setCreated(rs.getLong(5));
                bb.setSize(rs.getDouble(6));
                bb.setCompressionMethod(rs.getInt(7));
                bb.setEncryptionMethod(rs.getInt(8));
                bb.setIsCustomPassword(rs.getBoolean(9));

                backupList.add(bb.build());
            }
            return backupList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public Backup getById(String id){
        String query = "SELECT * FROM " + this.tableName + " WHERE id=?";
        try {
            PreparedStatement ps = this.dbHelper.getConnection().prepareStatement(query);
            ps.setString(1,id);

            ResultSet rs = ps.executeQuery();

            BackupBuilder bb = new BackupBuilder();
            
            while (rs.next()) {
                bb.reset();

                bb.setId(rs.getString(1));
                bb.setName(rs.getString(2));
                bb.setComment(rs.getString(3));
                bb.setPath(rs.getString(4));
                bb.setCreated(rs.getLong(5));
                bb.setSize(rs.getDouble(6));
                bb.setCompressionMethod(rs.getInt(7));
                bb.setEncryptionMethod(rs.getInt(8));
                bb.setIsCustomPassword(rs.getBoolean(9));
            }
            return bb.build();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public Boolean add(Backup backup){
        String query = "INSERT INTO " + DatabaseConfiguration.BACKUPS_TABLENAME + " (id, name, comment, path, created, size, compression_method, encryption_method, is_custom_password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            this.dbHelper.beginTransaction();
            PreparedStatement ps = this.dbHelper.getConnection().prepareStatement(query);
            ps.setString(1,backup.getId().toString());
            ps.setString(2,backup.getName());
            ps.setString(3,backup.getComment());
            ps.setString(4,backup.getPath());

            ps.setLong(5,backup.getCreated());
            ps.setDouble(6,backup.getSize());
            ps.setInt(7,backup.getCompressionMethod());
            ps.setInt(8,backup.getEncryptionMethod());
            ps.setBoolean(9,backup.getIsCustomPassword());
            ps.executeUpdate();
            this.dbHelper.commitTransaction();
            return true;
        } catch (SQLException e) {
            this.dbHelper.rollbackTransaction();
            e.printStackTrace();
            return false;
        }
    }
}

