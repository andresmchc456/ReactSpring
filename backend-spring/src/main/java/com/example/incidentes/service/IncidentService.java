//Su función principal es gestionar las reglas de negocio y las operaciones 
//CRUD (crear, consultar, actualizar y eliminar) de los incidentes, aislando al controlador de la forma en que se almacenan o procesan los datos.
package com.example.incidentes.service;

import com.example.incidentes.model.Incident;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class IncidentService {
    private final List<Incident> incidents = new ArrayList<>();
    private final AtomicLong sequence = new AtomicLong(4);

    public IncidentService() {
        incidents.add(new Incident(1L, "Sin conexión a Internet",
                "El equipo no puede acceder a recursos externos.",
                "Redes", "Alta", "Abierto", "Juan Pérez", "María Gómez"));
        incidents.add(new Incident(2L, "Aplicación no inicia",
                "La aplicación corporativa muestra un error al iniciar.",
                "Software", "Media", "En progreso", "Laura Ruiz", ""));
        incidents.add(new Incident(3L, "Solicitud de acceso",
                "Se requiere acceso a una carpeta compartida.",
                "Accesos", "Baja", "Resuelto", "Carlos Díaz", "Andrés López"));
    }

    public List<Incident> findAll() {
        return new ArrayList<>(incidents);
    }

    public Incident findById(Long id) {
        return incidents.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Incident create(Incident incident) {
        incident.setId(sequence.getAndIncrement());
        incidents.add(incident);
        return incident;
    }

    public Incident update(Long id, Incident data) {
        Incident current = findById(id);
        if (current == null)
            return null;

        current.setTitle(data.getTitle());
        current.setDescription(data.getDescription());
        current.setCategory(data.getCategory());
        current.setPriority(data.getPriority());
        current.setStatus(data.getStatus());
        current.setRequester(data.getRequester());
        current.setTechnician(data.getTechnician());
        return current;
    }

    public boolean delete(Long id) {
        return incidents.removeIf(i -> i.getId().equals(id));
    }
}