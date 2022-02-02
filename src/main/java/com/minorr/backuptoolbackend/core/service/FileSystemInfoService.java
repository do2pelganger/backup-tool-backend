package com.minorr.backuptoolbackend.core.service;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileSystemInfoService {
    private SystemInfo systemInfo;
    private List<OSFileStore> osFileStores;
    private List<HWDiskStore> hwDiskStores;

    @Autowired
    public FileSystemInfoService(){
        this.systemInfo = new SystemInfo();
        initOSFileStores();
        initHWDiskStores();
    }

    private Boolean initOSFileStores(){
        try{
            OperatingSystem operatingSystem = this.systemInfo.getOperatingSystem();
            FileSystem fileSystem = operatingSystem.getFileSystem();
            this.osFileStores = fileSystem.getFileStores();
        }catch(Exception e){
            this.osFileStores = null;
            return false;
        }
        return true;
    }
    private Boolean initHWDiskStores(){
        try{
            HardwareAbstractionLayer hal = this.systemInfo.getHardware();
            this.hwDiskStores = hal.getDiskStores();
        }catch(Exception e){
            this.hwDiskStores = null;
            return false;
        }
        return true;
    }
    public ArrayList<Disk> getDisksInfo(){
        ArrayList<Disk> disks = new ArrayList<Disk>(); 
        DiskBuilder diskBuilder = new DiskBuilder();
        HWDiskStore hwDStore;

        for(OSFileStore fileStore : this.osFileStores) {
            diskBuilder.reset();

            diskBuilder.setLabel(fileStore.getLabel());
            diskBuilder.setMount(fileStore.getMount());
            diskBuilder.setType(fileStore.getName());
            diskBuilder.setFileSystem(fileStore.getType());
            diskBuilder.setUsed((fileStore.getTotalSpace() - fileStore.getFreeSpace()));
            diskBuilder.setFree(fileStore.getFreeSpace());
            
            hwDStore = null;

            for(HWDiskStore store : this.hwDiskStores){
                List<HWPartition> partitions = store.getPartitions();
                for(HWPartition partition : partitions){
                    if(partition.getMountPoint().equals(fileStore.getMount())){
                        hwDStore = store;
                    }
                }
            }
            if(hwDStore != null){
                diskBuilder.setModel(hwDStore.getModel());
            }
            disks.add(diskBuilder.build());            

        }
        return disks;
    }
}