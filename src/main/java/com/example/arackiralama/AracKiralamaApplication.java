package com.example.arackiralama;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Bu notasyon, bunun bir Spring Boot uygulaması olduğunu belirtir
// ve otomatik konfigürasyonları etkinleştirir.
@SpringBootApplication
public class AracKiralamaApplication {

    // Java'daki standart başlangıç metodu. Projenin fitilini ateşler.
    public static void main(String[] args) {
        // Spring Boot uygulamasını çalıştırır.
        SpringApplication.run(AracKiralamaApplication.class, args);
    }
}