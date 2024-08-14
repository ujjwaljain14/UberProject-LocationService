package com.example.uberprojectlocationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
//@EntityScan("com.example.uberprojectentityservice.models")
public class UberProjectLocationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UberProjectLocationServiceApplication.class, args);
    }

}
