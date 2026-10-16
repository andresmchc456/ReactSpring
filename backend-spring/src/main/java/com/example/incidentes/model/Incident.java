//Su función principal es definir la estructura, los atributos y las reglas de validación de un incidente, 
//sirviendo como el molde de datos que viaja entre el cliente (JSON) y las capas del backend (Controller, Service y Repository).
package com.example.incidentes.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Incident { // clase que representa un incidente
    private Long id; // id del incidente

    @NotBlank // indica que este campo no puede estar vacio
    private String title; // titulo del incidente

    @NotBlank
    private String description; // descripción del incidente

    @NotBlank
    private String category; // categoría del incidente

    @NotBlank
    private String priority; // prioridad del incidente

    @NotBlank
    private String status; // estado del incidente

    @NotBlank
    private String requester; // solicitante del incidente

    private String technician; // técnico asignado al incidente

    public Incident() { // constructor por defecto
    }

    public Incident(Long id, String title, String description, String category, // constructor completo
            String priority, String status, String requester, String technician) { // parametros del constructor
        this.id = id; // id del incidente
        this.title = title; // titulo del incidente
        this.description = description; // descripción del incidente
        this.category = category; // categoría del incidente
        this.priority = priority; // prioridad del incidente
        this.status = status; // estado del incidente
        this.requester = requester; // solicitante del incidente
        this.technician = technician; // técnico asignado al incidente
    }

    public Long getId() { // getter del id
        return id;
    }

    public void setId(Long id) { // setter del id
        this.id = id;
    }

    public String getTitle() {
        return title; // getter del titulo
    }

    public void setTitle(String title) { // setter del titulo
        this.title = title;
    }

    public String getDescription() {
        return description; // getter de la descripción
    }

    public void setDescription(String description) { // setter de la descripción
        this.description = description;
    }

    public String getCategory() {
        return category; // getter de la categoría
    }

    public void setCategory(String category) { // setter de la categoría
        this.category = category;
    }

    public String getPriority() {
        return priority; // getter de la prioridad
    }

    public void setPriority(String priority) { // setter de la prioridad
        this.priority = priority;
    }

    public String getStatus() {
        return status; // getter del estado
    }

    public void setStatus(String status) { // setter del estado
        this.status = status;
    }

    public String getRequester() {
        return requester; // getter del solicitante
    }

    public void setRequester(String requester) { // setter del solicitante
        this.requester = requester;
    }

    public String getTechnician() {
        return technician; // getter del técnico
    }

    public void setTechnician(String technician) { // setter del técnico
        this.technician = technician;
    }
}