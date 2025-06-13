package com.developwithhomer.pcmexporter;

import com.developwithhomer.pcmexporter.repository.CyclistRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.developwithhomer"})
@Slf4j
public class PcmexporterApplication implements ApplicationRunner {

    @Value("${dbValue}")
    private String carrerFileName;

    @Value("${pcmuser}")
    private String userPcm;

    @Autowired
    private CyclistRepository cyclistRepository;

    public static void main(String[] args) {
        SpringApplication.run(PcmexporterApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Career filename: {}", carrerFileName);
        log.info("Pro cycling manager user: {}", userPcm);
        log.info("Startup application!");
    }
}
