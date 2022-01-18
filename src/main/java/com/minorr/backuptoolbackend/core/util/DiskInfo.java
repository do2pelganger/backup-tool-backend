package com.minorr.backuptoolbackend.core.util;

import java.io.File;

import javax.swing.filechooser.FileSystemView;

public class DiskInfo {
    public static void main(String[] args) {
        FileSystemView fsv = FileSystemView.getFileSystemView();
        File[] paths = File.listRoots();

// for each pathname in pathname array
        for(File path:paths)
        {
            // prints file and directory paths
            System.out.println("Drive Name: "+path);
            System.out.println("Description: "+fsv.getSystemTypeDescription(path));
            // System.out.println(fsv.toString());
            System.out.println("Total space: " + path.getTotalSpace()/(1024 * 1024 * 1024) +" GB");
            System.out.println("Free space: " + path.getFreeSpace()/(1024 * 1024 * 1024) +" GB");
            System.out.println("-------------------------------------------------------------");

        }
    }
}
