package com.example.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class App {
    @GetMapping("/")
    public String home() {
        return "Hello from Spring Boot App!";
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
