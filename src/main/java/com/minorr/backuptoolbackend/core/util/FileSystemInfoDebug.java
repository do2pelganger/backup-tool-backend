package com.minorr.backuptoolbackend.core.util;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HWPartition;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;
import oshi.util.FormatUtil;
import java.util.ArrayList;
import java.util.List;
import com.minorr.backuptoolbackend.core.model.Disk;
import com.minorr.backuptoolbackend.core.util.DiskBuilder;

public class FileSystemInfoDebug {
    public static void main(String... args) {
        SystemInfo systemInfo = new SystemInfo();
        HardwareAbstractionLayer hal = systemInfo.getHardware();

        OperatingSystem operatingSystem = systemInfo.getOperatingSystem();
        FileSystem fileSystem = operatingSystem.getFileSystem();
        List<OSFileStore> osFileStores = fileSystem.getFileStores();
        List<HWDiskStore> hwDiskStores = hal.getDiskStores();
        HWPartition fsPartition;

        ArrayList<Disk> disks = new ArrayList<Disk>(); 
        DiskBuilder diskBuilder = new DiskBuilder();
        System.out.println("length: " + osFileStores.size());
        for(OSFileStore fileStore : osFileStores) {
            System.out.println("Description: " + fileStore.getDescription());
            System.out.println("Label: " + fileStore.getLabel());
            System.out.println("Logical Volume: " + fileStore.getLogicalVolume());
            System.out.println("Mount: " + fileStore.getMount());
            System.out.println("Name: " + fileStore.getName());
            System.out.println("Options: " + fileStore.getOptions());
            System.out.println("Type: " + fileStore.getType());
            System.out.println("UUID: " + fileStore.getUUID());
            System.out.println("Volume: " + fileStore.getVolume());
            System.out.println("Free Space: " + FormatUtil.formatBytes(fileStore.getFreeSpace()));
            System.out.println("Total Space: " + FormatUtil.formatBytes(fileStore.getTotalSpace()));
            System.out.println("Usable Space: " + FormatUtil.formatBytes(fileStore.getUsableSpace()));
            fsPartition = null;
            for(HWDiskStore store : hwDiskStores){
                
                List<HWPartition> partitions = store.getPartitions();
                for(HWPartition partition : partitions){
    
                    
                    if(partition.getMountPoint().equals(fileStore.getMount())){
                        fsPartition = partition;
                        System.out.println("Name: " + store.getName());
                        System.out.println("Model: " + store.getModel());
                        System.out.println("Partition:  " + partition.getMountPoint());
                        System.out.println("            Size: " + FormatUtil.formatBytes(partition.getSize()));

                    }
    
                }
            }
            if(fsPartition != null){
                
            }
            System.out.println("-----------------------------------------------------");


        }

        
    }
}