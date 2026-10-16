//este codigo es el controlador y se comunica con el frontend
//Su función principal es exponer los endpoints HTTP de la API REST, recibir las peticiones del cliente (React), validar los datos de entrada y responder con los códigos de estado HTTP adecuados delegando la lógica a la capa de servicio.
package com.example.incidentes.controller;

import com.example.incidentes.model.Incident;
import com.example.incidentes.service.IncidentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // indica que esta clase es un controlador REST
@RequestMapping("/api/incidents") // indica que esta clase es un controlador REST
@CrossOrigin(origins = "*") // permite que cualquier origen se conecte a esta API
public class IncidentController {

    private final IncidentService service; // servicio que se encarga de la logica del negocio

    public IncidentController(IncidentService service) { // constructor que recibe el servicio
        this.service = service;
    }

    @GetMapping
    public List<Incident> findAll() { // devuelve todos los incidentes
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Incident> findById(@PathVariable Long id) { // devuelve un incidente por id
        Incident incident = service.findById(id); // busca el incidente por id
        return incident == null // si el incidente no existe
                ? ResponseEntity.notFound().build() // devuelve 404
                : ResponseEntity.ok(incident); // devuelve el incidente
    }

    @PostMapping
    public ResponseEntity<Incident> create(@Valid @RequestBody Incident incident) {// crea un nuevo incidente y devuelve
                                                                                   // 201 si es exitoso
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(incident)); // crea el incidente y devuelve
                                                                                         // 201
    }

    @PutMapping("/{id}") // actualiza un incidente por id
    public ResponseEntity<Incident> update(// recibe el id del incidente y el incidente a actualizar
            @PathVariable Long id, // id del incidente
            @Valid @RequestBody Incident incident) { // incidente a actualizar
        Incident updated = service.update(id, incident); // actualiza el incidente
        return updated == null // si el incidente no existe
                ? ResponseEntity.notFound().build() // devuelve 404
                : ResponseEntity.ok(updated); // devuelve el incidente actualizado
    }

    @DeleteMapping("/{id}") // elimina un incidente por id
    public ResponseEntity<Void> delete(@PathVariable Long id) { // recibe el id del incidente
        return service.delete(id) // elimina el incidente
                ? ResponseEntity.noContent().build() // devuelve 204
                : ResponseEntity.notFound().build(); // devuelve 404
    }
}