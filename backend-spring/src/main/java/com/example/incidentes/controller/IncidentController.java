package com.example.incidentes.controller;

import com.example.incidentes.model.Incident;
import com.example.incidentes.service.IncidentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incidents")
@CrossOrigin(origins = "*")
public class IncidentController {

    private final IncidentService service;

    public IncidentController(IncidentService service) {
        this.service = service;
    }

    @GetMapping
    public List<Incident> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Incident> findById(@PathVariable Long id) {
        Incident incident = service.findById(id);
        return incident == null
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(incident);
    }

    @PostMapping
    public ResponseEntity<Incident> create(@Valid @RequestBody Incident incident) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(incident));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Incident> update(
            @PathVariable Long id,
            @Valid @RequestBody Incident incident) {
        Incident updated = service.update(id, incident);
        return updated == null
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return service.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}