package com.minorr.backuptoolbackend.core.controller;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import com.minorr.backuptoolbackend.core.config.StorageConfiguration;
import com.minorr.backuptoolbackend.core.repository.SettingsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoProperties.Storage;
import org.springframework.stereotype.Controller;

@Controller
public class SettingsController {

    private SettingsRepository settingsRepository;

    @Autowired
    public SettingsController(SettingsRepository settingsRepository){
        this.settingsRepository = settingsRepository;
    }

    public String getMasterPasswordHash(){
        return this.settingsRepository.getMasterPasswordHash();
    }

    public Boolean isMasterPassCorrect(String input){
        System.out.println("FROM DATABASE -> " + this.settingsRepository.getMasterPasswordHash());
        System.out.println("FROM INPUT -> " + this.stringToHashString(input));
        return this.settingsRepository.getMasterPasswordHash().equals(this.stringToHashString(input));
    }

    private String stringToHashString(String input){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            return this.bytesToHex(hash);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
    }
    private String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public String getStorageFolder() {
        String storageFolder = this.settingsRepository.getStorageFolder();
        if(storageFolder == null){
            storageFolder = StorageConfiguration.STORAGE;
        }
        return storageFolder;
    }
}
