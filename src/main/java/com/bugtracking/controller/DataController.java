package com.bugtracking.controller;

import com.bugtracking.model.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DataController {

    // Single JSON object
    @GetMapping("/user")
    public User getUser() {
        return new User(1, "Radhika", "radhika@example.com");
    }

    // Array of JSON objects
    @GetMapping("/users")
    public List<User> getUsers() {
        return List.of(
                new User(1, "Radhika", "radhika@example.com"),
                new User(2, "Amit", "amit@example.com")
        );
    }

    // Plain text
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello from Spring Boot!";
    }

    // XML
    @GetMapping(value = "/userxml", produces = MediaType.APPLICATION_XML_VALUE)
    public User getUserXML() {
        return new User(1, "Radhika", "radhika@example.com");
    }

    // CSV
    @GetMapping(value = "/userscsv", produces = "text/csv")
    public String getUsersCSV() {
        return "id,name,email\n1,Radhika,radhika@example.com\n2,Amit,amit@example.com";
    }
}
