import axios from "axios";

const api = axios.create({
  baseURL: "http://localhost:8080/api",
  headers: { "Content-Type": "application/json" }
});

export const getIncidents = () => api.get("/incidents");
export const getIncident = (id) => api.get(`/incidents/${id}`);
export const createIncident = (incident) => api.post("/incidents", incident);
export const updateIncident = (id, incident) => api.put(`/incidents/${id}`, incident);
export const deleteIncident = (id) => api.delete(`/incidents/${id}`);