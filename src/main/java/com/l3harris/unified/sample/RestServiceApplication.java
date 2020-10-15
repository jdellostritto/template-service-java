package com.l3harris.unified.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestServiceApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestServiceApplication.class);

    public static void main(String[] args) {
        
        SpringApplication.run(RestServiceApplication.class, args);
        LOGGER.info("Application Started: {} ", LocalDateTime.now());
        LOGGER.debug("Application Started: {} ", LocalDateTime.now());
        LOGGER.error("Application Started: {} ", LocalDateTime.now());
    }

}
