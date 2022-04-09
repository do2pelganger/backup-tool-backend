package com.minorr.backuptoolbackend.core.controller;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.concurrent.TimeUnit;

import com.minorr.backuptoolbackend.core.config.StorageConfiguration;
import com.minorr.backuptoolbackend.core.model.ProgressStatus;
import com.minorr.backuptoolbackend.core.util.ProgressStatusBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.CompressionMethod;
import net.lingala.zip4j.model.enums.EncryptionMethod;
import net.lingala.zip4j.progress.ProgressMonitor;

/**
 * @TODO
 * check possible memory leak (ProgressMonitor)
 */
@Controller
public class ZipController {

    private ZipParameters params;
    private ProgressMonitor progressMonitor;
    private String passwordHash;

    public ZipController(){
        this.passwordHash = null;
        this.params = new ZipParameters();
        init();
    }

    private void init(){
        if(this.params != null) {
            this.params.setCompressionMethod(CompressionMethod.DEFLATE);
        }
    }

    public void enableEncryption(String passwordHash){
        this.passwordHash = passwordHash;
        System.out.println("encryption enabled with password " + passwordHash);
        this.params.setCompressionMethod(CompressionMethod.DEFLATE);
        this.params.setEncryptFiles(true);
        this.params.setEncryptionMethod(EncryptionMethod.AES);
        // Below line is optional. AES 256 is used by default. You can override it to use AES 128. AES 192 is supported only for extracting.
        this.params.setAesKeyStrength(AesKeyStrength.KEY_STRENGTH_256);
    }

    public Boolean zip(String sourceFolder, String zipFilePath){
        try{
            ZipFile zf =  new ZipFile(zipFilePath);
            this.progressMonitor = zf.getProgressMonitor();
            // zf.setRunInThread(true);
            if(this.passwordHash != null){
                System.out.println("password has been set " + this.passwordHash);
                zf.setPassword(this.passwordHash.toCharArray());
            }
            zf.addFolder(new File(sourceFolder), this.params);
            zf.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            throw new UncheckedIOException(e);
        }
    }

    public Boolean unzip(String zipFilePath, String destDir){
        try {
            ZipFile zf =  new ZipFile(zipFilePath);
            this.progressMonitor = zf.getProgressMonitor();
            zf.setRunInThread(true);  
            if(this.passwordHash != null){
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

    public ProgressStatus getProgress(){
        ProgressStatusBuilder psb = new ProgressStatusBuilder();
        psb.setCurrentFileName(this.progressMonitor.getFileName());
        psb.setPercentDone(this.progressMonitor.getPercentDone());
        return psb.build();
    }

    // public static void main(String[] args) throws IOException {
    //     ZipController cc = new ZipController(StorageConfiguration.DEBUG_PASSWORD_HASH);
    //     String sourceFolder = "C:\\Users\\montr\\Pictures\\_13456";
    //     String zipFilePath = "M:\\dev\\projects\\backup-tool-backend\\storage\\_13456.wbck";
    //     long startTime = System.nanoTime();
    //     cc.zip(sourceFolder, zipFilePath, StorageConfiguration.DEBUG_PASSWORD_HASH);
    //     long endTime   = System.nanoTime();
    //     long totalTime = endTime - startTime;

    //     System.out.println("Zip time: " + TimeUnit.SECONDS.convert(totalTime, TimeUnit.NANOSECONDS));
    //     // startTime = System.nanoTime();

    //     // cc.unzip(zipFilePath, sourceFolder + "_UNZIPPED");
    //     // endTime   = System.nanoTime();
    //     // totalTime = endTime - startTime;

    //     // System.out.println("Unzip time: " + TimeUnit.SECONDS.convert(totalTime, TimeUnit.NANOSECONDS));
    // }
}