package com.developwithhomer.pcmexporter.db.utility;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Slf4j
@Component
public class PCMExporterUtility {

    @Value("${dbValue}")
    private String carrerFileName;

    @Value("${pcmuser}")
    private String userPcm;

    @Value("${gameVersion}")
    private String gameVersion;

    @Value("${enable_test}")
    private String enableCopyFile;

    public void exportDBPcm()throws IOException {
        log.info("User pro cycling manager: {}", userPcm);
        log.info("Build Pro cycling manager directory saved game");
        getCurrentLocation();
        String dbPcmDirectory = getDBPCM(userPcm);
        log.info("Get pro cycling manager directory: {}", dbPcmDirectory);
        File exporterFile = new File(getCurrentLocation() + "/exporter");
        String commandFile = exporterFile.getPath() + "\\SQLiteExporter -export \"Pro Cycling Manager "+gameVersion+"\\Cloud\\" + userPcm + "\\" + carrerFileName + ".cdb\"";
        Runtime.getRuntime().exec(commandFile, null, exporterFile);
    }

    public void copyDBFile()throws IOException{
        if(StringUtils.isEmpty(enableCopyFile) || "true".equals(enableCopyFile)){
            String dbPcmDirectory = getDBPCM(userPcm);
            File source = new File(dbPcmDirectory + "\\" + carrerFileName + ".sqlite");
            File destination = new File(getCurrentLocation()+ "/db_data/" + carrerFileName + ".db");
            log.info("Source folder: {}", source.getAbsolutePath());
            log.info("Destination folder: {}", destination.getAbsolutePath());
            FileUtils.copyFile(source, destination);
        }
    }

    private String getDBPCM(String userPcm) {
        String dbPcmDirectory = "C:\\Users\\userWindow\\AppData\\Roaming\\Pro Cycling Manager "+ gameVersion +"\\Cloud\\userPCM";
        dbPcmDirectory = dbPcmDirectory.replace("userWindow", getUserWindowsName()).replace("userPCM", userPcm);
        return dbPcmDirectory;
    }

    public String getCurrentLocation(){
        String location = System.getProperty("user.dir");
        log.info("Current location: {}", location);
        return location;
    }

    private String getUserWindowsName(){
        return System.getProperty("user.name");
    }

}
