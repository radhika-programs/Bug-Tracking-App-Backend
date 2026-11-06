package com.bugtracking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BugTrackingApiApplication {

    // Define a logger for this class
    private static final Logger LOGGER = LoggerFactory.getLogger(BugTrackingApiApplication.class);

    public static void main(String[] args) {
        // Log a debug message when the application starts loading
        LOGGER.debug("Hey There !!! Bug Tracking Application Started Loading ... !!!");

        // Start the Spring Boot application
        SpringApplication.run(BugTrackingApiApplication.class, args);

        // Log an info message when the application is running
        LOGGER.info("BUG-TRACKING APPLICATION IS RUNNING NOW !!!");
    }
}
