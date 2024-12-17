package com.jackson.myblogminisystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "com.jackson")
@EnableScheduling
public class MyblogminiSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyblogminiSystemApplication.class, args);
    }

}
