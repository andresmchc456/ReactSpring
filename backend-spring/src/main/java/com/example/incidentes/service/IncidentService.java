//Su función principal es gestionar las reglas de negocio y las operaciones 
//CRUD (crear, consultar, actualizar y eliminar) de los incidentes, aislando al controlador de la forma en que se almacenan o procesan los datos.
package com.example.incidentes.service;

import com.example.incidentes.model.Incident;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service // Indica que esta clase es un servicio
public class IncidentService { // clase que representa un servicio
    private final List<Incident> incidents = new ArrayList<>(); // lista de incidentes
    private final AtomicLong sequence = new AtomicLong(4); // secuencia de ids

    public IncidentService() { // constructor que recibe el servicio
        incidents.add(new Incident(1L, "Sin conexión a Internet", // id del incidente
                "El equipo no puede acceder a recursos externos.",
                "Redes", "Alta", "Abierto", "Juan Pérez", "María Gómez"));
        incidents.add(new Incident(2L, "Aplicación no inicia",
                "La aplicación corporativa muestra un error al iniciar.",
                "Software", "Media", "En progreso", "Laura Ruiz", ""));
        incidents.add(new Incident(3L, "Solicitud de acceso",
                "Se requiere acceso a una carpeta compartida.",
                "Accesos", "Baja", "Resuelto", "Carlos Díaz", "Andrés López"));
    }

    public List<Incident> findAll() { // devuelve todos los incidentes
        return new ArrayList<>(incidents);
    }

    public Incident findById(Long id) { // devuelve un incidente por id
        return incidents.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Incident create(Incident incident) { // crea un nuevo incidente
        incident.setId(sequence.getAndIncrement()); // asigna un id al incidente
        incidents.add(incident); // agrega el incidente a la lista
        return incident;
    }

    public Incident update(Long id, Incident data) { // actualiza un incidente
        Incident current = findById(id); // busca el incidente por id
        if (current == null) // si el incidente no existe
            return null; // devuelve null

        current.setTitle(data.getTitle()); // actualiza el titulo del incidente
        current.setDescription(data.getDescription()); // actualiza la descripción del incidente
        current.setCategory(data.getCategory()); // actualiza la categoría del incidente
        current.setPriority(data.getPriority()); // actualiza la prioridad del incidente
        current.setStatus(data.getStatus()); // actualiza el estado del incidente
        current.setRequester(data.getRequester()); // actualiza el solicitante del incidente
        current.setTechnician(data.getTechnician()); // actualiza el técnico asignado al incidente
        return current; // devuelve el incidente actualizado
    }

    public boolean delete(Long id) { // elimina un incidente por id
        return incidents.removeIf(i -> i.getId().equals(id)); // elimina el incidente
    }
}