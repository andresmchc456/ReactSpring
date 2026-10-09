from fastapi import FastAPI, HTTPException
from fastapi.middleware.cors import CORSMiddleware
from pydantic import BaseModel, Field
from typing import Optional

# 1. Instancia principal de la aplicación
app = FastAPI(
    title="Gestión de Incidentes API",
    description="API REST en Python con FastAPI para el proyecto RectSpring",
    version="1.0.0"
)

# 2. Configurar CORS (Crucial para conectar con React)
app.add_middleware(
    CORSMiddleware,
    allow_origins=["http://localhost:5173", "http://localhost:3000", "*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

# 3. Ruta principal de prueba
@app.get("/")
def home():
    return {
        "mensaje": "API de Gestión de Incidentes funcionando correctamente",
        "documentacion": "/docs"
    }

# 4. Modelo que define cómo luce un Incidente con Pydantic
class Incident(BaseModel):
    id: Optional[int] = None
    title: str = Field(min_length=1)
    description: str = Field(min_length=1)
    category: str = Field(min_length=1)
    priority: str = Field(min_length=1)
    status: str = Field(min_length=1)
    requester: str = Field(min_length=1)
    technician: Optional[str] = ""

# 5. Lista temporal en memoria (Simulación de la BD)
incidents = [
    Incident(
        id=1,
        title="Sin conexión a Internet",
        description="El equipo no puede acceder a recursos externos.",
        category="Redes",
        priority="Alta",
        status="Abierto",
        requester="Juan Pérez",
        technician="María Gómezsss"
    ),
    Incident(
        id=2,
        title="Aplicación no inicia",
        description="La aplicación corporativa muestra un error.",
        category="Software",
        priority="Media",
        status="En progreso",
        requester="Laura Ruiz",
        technician=""
    )
]

next_id = 3

# 6. Endpoints CRUD

# GET /api/incidents - Obtener todos los incidentes
@app.get("/api/incidents")
def get_all_incidents():
    return incidents

# GET /api/incidents/{incident_id} - Obtener incidente por ID
@app.get("/api/incidents/{incident_id}")
def get_incident_by_id(incident_id: int):
    for incident in incidents:
        if incident.id == incident_id:
            return incident
    raise HTTPException(status_code=404, detail="Incidente no encontrado")

# POST /api/incidents - Crear un nuevo incidente
@app.post("/api/incidents", status_code=201)
def create_incident(incident: Incident):
    global next_id
    incident.id = next_id
    next_id += 1
    incidents.append(incident)
    return incident

# PUT /api/incidents/{incident_id} - Actualizar un incidente
@app.put("/api/incidents/{incident_id}")
def update_incident(incident_id: int, updated_data: Incident):
    for index, incident in enumerate(incidents):
        if incident.id == incident_id:
            updated_data.id = incident_id
            incidents[index] = updated_data
            return updated_data
    raise HTTPException(status_code=404, detail="Incidente no encontrado")

# DELETE /api/incidents/{incident_id} - Eliminar un incidente
@app.delete("/api/incidents/{incident_id}", status_code=204)
def delete_incident(incident_id: int):
    for index, incident in enumerate(incidents):
        if incident.id == incident_id:
            incidents.pop(index)
            return
    raise HTTPException(status_code=404, detail="Incidente no encontrado")
