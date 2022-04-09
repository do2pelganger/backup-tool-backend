package com.minorr.backuptoolbackend.core.controller;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.UncheckedIOException;
// import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;

@Controller
public class FileManagerController {
    public Boolean copyDirTo(String dirPath, String destPath){
        try {
            System.out.println("Copying directory: " + dirPath + " to " + destPath);
            File dir = new File(dirPath);
            File dest = new File(destPath);
            FileUtils.copyDirectory(dir, dest);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            throw new UncheckedIOException(e);
        }
    }
    //delete all files with name prefix
    public Boolean deleteFilesWithPrefix(String storagePath, String prefix){
        try {
            File storage = new File(storagePath);
            File[] files = storage.listFiles( new FilenameFilter() {
                @Override
                public boolean accept( final File dir,
                                       final String name ) {
                    return name.matches( prefix + '*' );
                }
            } );
            for (File file : files) {
                FileUtils.forceDelete(file);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            throw new UncheckedIOException(e);
        }
    }
    public Boolean deleteFile(String filePath){
        try {
            System.out.println("Deleting file: " + filePath);
            File file = new File(filePath);
            FileUtils.forceDelete(file);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            throw new UncheckedIOException(e);
        }
    }

    // public static void main(String[] args) {
    //     long startTime = System.nanoTime();

    //     FileManagerController controller = new FileManagerController();
    //     controller.copyDirTo("C:\\Users\\montr\\Pictures\\_13456","C:\\Users\\montr\\Pictures\\_TESTIM");


    //     long endTime   = System.nanoTime();
    //     long totalTime = endTime - startTime;

    //     System.out.println("Runtime: " + TimeUnit.SECONDS.convert(totalTime, TimeUnit.NANOSECONDS));
    // }
}
