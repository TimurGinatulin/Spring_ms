package ru.ginatulin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "ru.ginatulin")
public class StorageServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(StorageServiceApp.class, args);
    }
}
