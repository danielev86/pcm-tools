package com.developwithhomer.pcmexporter.config;

import com.developwithhomer.pcmexporter.db.utility.PCMExporterUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@Slf4j
public class DatabaseConfiguration {

    @Autowired
    private PCMExporterUtility pcmExporterUtility;

    @Value("${dbValue}")
    private String carrerFileName;

    @Value("${pcmuser}")
    private String userPcm;

    @Bean
    public DataSource getDataSource() {
        log.info("Initialize spring datasource");
        initializeDatabase();
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.sqlite.JDBC");
        dataSourceBuilder.url("jdbc:sqlite:" + pcmExporterUtility.getCurrentLocation()+ "/db_data/"  + carrerFileName +".db" );
        dataSourceBuilder.username("");
        dataSourceBuilder.password("");
        return dataSourceBuilder.build();
    }

    private void initializeDatabase(){
        try{
            pcmExporterUtility.exportDBPcm();
            pcmExporterUtility.copyDBFile();
        }catch (Exception e){
            log.error("Error initialize database!", e);
        }
    }

}
