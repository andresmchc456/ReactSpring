// Punto de entrada principal de la aplicación
import React from "react";
import { createRoot } from "react-dom/client";
import App from "./App";
import "./styles.css";

createRoot(document.getElementById("root")).render( //renderizado de la aplicacion
  <React.StrictMode><App /></React.StrictMode>
);