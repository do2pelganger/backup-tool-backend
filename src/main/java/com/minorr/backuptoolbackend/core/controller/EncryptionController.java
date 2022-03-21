package com.minorr.backuptoolbackend.core.controller;

import java.io.FileOutputStream;
import java.io.File;
import java.io.FileInputStream;

import java.math.BigInteger;

import com.minorr.backuptoolbackend.core.config.StorageConfiguration;
import com.minorr.backuptoolbackend.core.model.Backup;

import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.encrypt.Encryptors;

/**
 * encrypt/decrypt operations do not change file size
 */
public class EncryptionController {

    public File encrypt(Backup backup, String passwordHash) {
        return this.processOperation(backup, passwordHash, true);
    }
    public File decrypt(Backup backup, String passwordHash) {
        return this.processOperation(backup, passwordHash, false); 
    }

    private File processOperation(Backup backup, String passwordHash, Boolean isEncryption) {
        String inputAbsPath;
        String outputAbsPath;

        if(isEncryption){
            inputAbsPath = StorageConfiguration.STORAGE + backup.getName() + StorageConfiguration.COMPRESSED_EXT;
            outputAbsPath = StorageConfiguration.STORAGE + backup.getName() + StorageConfiguration.ENCRYPTED_EXT;
        }else{
            inputAbsPath = StorageConfiguration.STORAGE + backup.getName() + StorageConfiguration.ENCRYPTED_EXT;
            outputAbsPath = StorageConfiguration.STORAGE + backup.getName() + StorageConfiguration.COMPRESSED_EXT;
        }
        

        BytesEncryptor encryptor = Encryptors.stronger(passwordHash, this.toHex(backup.getName()));
        
        try{
            File inputFile = new File(inputAbsPath);
            File outputFile = new File(outputAbsPath);

            FileInputStream fis = new FileInputStream(inputFile);
            FileOutputStream fos = new FileOutputStream(outputFile);
    
            byte[] bFile = new byte[(int) inputFile.length()];
    
            fis.read(bFile);
            fis.close();
            inputFile.delete();

            if(isEncryption){
                fos.write(encryptor.encrypt(bFile));
            }else{
                fos.write(encryptor.decrypt(bFile));
            }

            fos.close();
            return outputFile;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }    
    }
    
    private String toHex(String str) {
        return String.format("%040x", new BigInteger(1, str.getBytes()));
    }
}
