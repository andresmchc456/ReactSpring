//componente de presentación y captura de datos (Controlled Form Component).
//Su función principal es servir como una interfaz interactiva de modo dual para crear nuevos incidentes o editar uno existente.
import React, { useEffect, useState } from "react";

const emptyIncident = { //objeto vacio que se usa para crear nuevos incidentes
  title: "",
  description: "",
  category: "Redes",
  priority: "Media",
  status: "Abierto",
  requester: "",
  technician: ""
};

export default function IncidentForm({ selected, onSaved, onCancel }) { //seleccionado, onSaved, onCancel son props
  const [form, setForm] = useState(emptyIncident); //estado del formulario
  const [saving, setSaving] = useState(false); //estado de guardado

  useEffect(() => { //hook que se ejecuta cuando cambia selected
    setForm(selected ? { ...selected } : emptyIncident); //actualiza el formulario con el incidente seleccionado
  }, [selected]);

  const handleChange = (e) => { //maneja los cambios en el formulario
    setForm({ ...form, [e.target.name]: e.target.value }); //actualiza el estado del formulario
  };

  const submit = async (e) => { //maneja el envio del formulario
    e.preventDefault();
    setSaving(true);
    try {
      await onSaved(form);
      setForm(emptyIncident);
    } finally {
      setSaving(false);
    }
  };

  return (//renderizado del formulario
    <form className="card form" onSubmit={submit}>
      <h2>{selected ? "Editar incidente" : "Nuevo incidente"}</h2>

      <label>Título
        <input name="title" value={form.title} onChange={handleChange} required />
      </label>

      <label>Descripción
        <textarea name="description" value={form.description} onChange={handleChange} required />
      </label>

      <div className="grid2">
        <label>Categoría
          <select name="category" value={form.category} onChange={handleChange}>
            <option>Redes</option><option>Hardware</option><option>Software</option><option>Accesos</option>
          </select>
        </label>

        <label>Prioridad
          <select name="priority" value={form.priority} onChange={handleChange}>
            <option>Baja</option><option>Media</option><option>Alta</option><option>Crítica</option>
          </select>
        </label>
      </div>

      <div className="grid2">
        <label>Estado
          <select name="status" value={form.status} onChange={handleChange}>
            <option>Abierto</option><option>En progreso</option><option>Resuelto</option><option>Cerrado</option>
          </select>
        </label>

        <label>Solicitante
          <input name="requester" value={form.requester} onChange={handleChange} required />
        </label>
      </div>

      <label>Técnico asignado
        <input name="technician" value={form.technician} onChange={handleChange} />
      </label>

      <div className="actions">
        <button disabled={saving}>{saving ? "Guardando..." : "Guardar"}</button>
        {selected && <button type="button" className="secondary" onClick={onCancel}>Cancelar</button>}
      </div>
    </form>
  );
}