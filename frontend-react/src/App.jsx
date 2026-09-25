import React, { useEffect, useState } from "react";
import IncidentForm from "./components/IncidentForm";
import IncidentList from "./components/IncidentList";
import {
  getIncidents, createIncident, updateIncident, deleteIncident
} from "./services/incidentService";

export default function App() {
  const [incidents, setIncidents] = useState([]);
  const [selected, setSelected] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  const load = async () => {
    try {
      setError("");
      setLoading(true);
      const response = await getIncidents();
      setIncidents(response.data);
    } catch (e) {
      setError("No fue posible consultar los incidentes.");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => { load(); }, []);

  const save = async (incident) => {
    try {
      if (selected) {
        await updateIncident(selected.id, incident);
      } else {
        await createIncident(incident);
      }
      setSelected(null);
      await load();
    } catch (e) {
      setError("No fue posible guardar el incidente.");
      throw e;
    }
  };

  const remove = async (id) => {
    if (!window.confirm("¿Desea eliminar este incidente?")) return;
    try {
      await deleteIncident(id);
      await load();
    } catch (e) {
      setError("No fue posible eliminar el incidente.");
    }
  };

  return (
    <main className="container">
      <header>
        <div>
          <p className="eyebrow">Taller Full Stack</p>
          <h1>Gestión de Incidentes</h1>
          <p>React + Vite + Axios / Spring Boot</p>
        </div>
      </header>

      {error && <div className="alert">{error}</div>}

      <section className="layout">
        <IncidentForm
          selected={selected}
          onSaved={save}
          onCancel={() => setSelected(null)}
        />
        <IncidentList
          incidents={incidents}
          loading={loading}
          onEdit={setSelected}
          onDelete={remove}
        />
      </section>
    </main>
  );
}