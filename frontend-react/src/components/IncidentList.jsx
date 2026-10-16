//Su función principal es renderizar la tabla con la lista de incidentes y disparar las acciones de edición y eliminación.
import React, { useEffect, useState } from "react";
export default function IncidentList({ incidents, onEdit, onDelete, loading }) { //incidents, onEdit, onDelete, loading son props = un molde 


  if (loading) return <div className="card">Cargando incidentes...</div>; //si esta cargando muestra un mensaje

  return (
    <div className="card">
      <div className="section-title">
        <h2>Incidentes</h2>
        <span>{incidents.length} registros</span>
      </div>

      <div className="table-wrap">
        <table>
          <thead>
            <tr>
              <th>ID</th><th>Título</th><th>Categoría</th><th>Prioridad</th>
              <th>Estado</th><th>Técnico</th><th>Acciones</th>
            </tr>
          </thead>
          <tbody>
            {incidents.map((i) => (
              <tr key={i.id}>
                <td>{i.id}</td>
                <td><strong>{i.title}</strong><br /><small>{i.requester}</small></td>
                <td>{i.category}</td>
                <td><span className="tag">{i.priority}</span></td>
                <td>{i.status}</td>
                <td>{i.technician || "Sin asignar"}</td>
                <td className="actions-inline">
                  <button onClick={() => onEdit(i)}>Editar</button>
                  <button className="danger" onClick={() => onDelete(i.id)}>Eliminar</button>
                </td>
              </tr>
            ))}
            {!incidents.length && <tr><td colSpan="7">No hay incidentes.</td></tr>}
          </tbody>
        </table>
      </div>
    </div>
  );
}