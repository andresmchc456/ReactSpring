package com.example.incidentes; // indica que esta clase es un servicio

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // Indica que esta clase es un servicio
public class IncidentesApplication { // clase que representa un servicio
    public static void main(String[] args) { // método principal
        SpringApplication.run(IncidentesApplication.class, args); // inicia la aplicación
    }
}