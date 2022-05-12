package com.minorr.backuptoolbackend.core.controller;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;

import org.springframework.stereotype.Controller;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.model.UnzipParameters;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.CompressionMethod;
import net.lingala.zip4j.model.enums.EncryptionMethod;

/**
 * @TODO
 * check possible memory leak (ProgressMonitor)
 */
@Controller
public class ZipController {

    private ZipParameters paramsZip;
    private String passwordHash;

    public ZipController(){
        this.passwordHash = null;
        this.paramsZip = new ZipParameters();
        init();
    }

    private void init(){
        if(this.paramsZip != null) {
            this.paramsZip.setCompressionMethod(CompressionMethod.DEFLATE);
        }
    }

    public void enableEncryption(String passwordHash){
        this.passwordHash = passwordHash;
        System.out.println("encryption enabled with password " + passwordHash);
        this.paramsZip.setCompressionMethod(CompressionMethod.DEFLATE);
        this.paramsZip.setEncryptFiles(true);
        this.paramsZip.setEncryptionMethod(EncryptionMethod.AES);
        // Below line is optional. AES 256 is used by default. You can override it to use AES 128. AES 192 is supported only for extracting.
        this.paramsZip.setAesKeyStrength(AesKeyStrength.KEY_STRENGTH_256);
        this.paramsZip.setReadHiddenFiles(false);
        this.paramsZip.setReadHiddenFolders(false);


    }

    public Boolean zip(String sourceFolder, String zipFilePath){
        try{
            ZipFile zf =  new ZipFile(zipFilePath);
            if(this.passwordHash != null){
                System.out.println("password has been set " + this.passwordHash);
                zf.setPassword(this.passwordHash.toCharArray());
            }
            zf.addFolder(new File(sourceFolder), this.paramsZip);
            zf.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            throw new UncheckedIOException(e);
        }
    }

    public Boolean unzip(String zipFilePath, String destDir){
        try {
            System.out.println("zipFilePath=" + zipFilePath + " destDir=" + destDir);
            ZipFile zf =  new ZipFile(zipFilePath);
            // zf.setRunInThread(true);  
            if(zf.isEncrypted() && this.passwordHash != null){
                System.out.println("setting password  " + this.passwordHash);
                zf.setPassword(this.passwordHash.toCharArray());
            }      
            zf.extractAll(destDir);
            zf.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            throw new UncheckedIOException(e);
        }
    }
}