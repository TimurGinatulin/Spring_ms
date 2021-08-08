package ru.ginatulin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "ru.ginatulin")
public class DeliveryServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(DeliveryServiceApp.class, args);
    }
}
