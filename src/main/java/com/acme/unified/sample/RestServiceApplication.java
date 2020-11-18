package com.acme.unified.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The Class SpringBoot Application.
 *
 * @author Jim.DelloStritto
 * @project template-service-java
 * @created Oct 17, 2020
 * @references
 * @credits pivotal.io
 */
@SpringBootApplication(scanBasePackages = {"com.acme.unified.sample"})
public class RestServiceApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestServiceApplication.class);

    public static void main(String[] args) {
        
        SpringApplication.run(RestServiceApplication.class, args);
        LOGGER.info("Application Started: {} ", LocalDateTime.now());
        LOGGER.debug("Application Started: {} ", LocalDateTime.now());
        LOGGER.error("Application Started: {} ", LocalDateTime.now());
    }

}
