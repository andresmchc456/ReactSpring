# Backend - Gestión de Incidentes

API REST construida con Spring Boot 3 y Java 17.

## Ejecutar

```bash
mvn spring-boot:run
```

La API queda disponible en:

http://localhost:8080

## Importante

No utiliza base de datos. Los incidentes se mantienen en memoria mientras la aplicación está ejecutándose.

## Endpoints

GET    /api/incidents
GET    /api/incidents/{id}
POST   /api/incidents
PUT    /api/incidents/{id}
DELETE /api/incidents/{id}
