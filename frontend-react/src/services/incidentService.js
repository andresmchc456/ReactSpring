// actúa como la capa de servicios (Service / API Client Layer) del frontend.
import axios from "axios";

const api = axios.create({ // crea una instancia de axios para hacer peticiones http
  baseURL: "http://localhost:8080/api", //url base de la api
  headers: { "Content-Type": "application/json" } //encabezado de la peticion
});

export const getIncidents = () => api.get("/incidents"); //obtener todos los incidentes
export const getIncident = (id) => api.get(`/incidents/${id}`); //obtener un incidente por id
export const createIncident = (incident) => api.post("/incidents", incident); //crear un incidente
export const updateIncident = (id, incident) => api.put(`/incidents/${id}`, incident); //actualizar un incidente
export const deleteIncident = (id) => api.delete(`/incidents/${id}`); //eliminar un incidente