package com.redcatdev86.pcm24dbcore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.redcatdev86"})
public class Pcm24DbCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(Pcm24DbCoreApplication.class, args);
    }

}
