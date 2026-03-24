# Categories Microservice (Kotlin)

Microservicio de gestión de categorías para e-commerce, desarrollado con Kotlin + Spring Boot.

## 🚀 Tecnologías

- **Kotlin 2.1.0**
- **Spring Boot 4.0.3**
- **Java 25** (JVM)
- **SQLite** (base de datos)
- **Docker**

## 📝 Endpoints

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/categories` | Listar todas las categorías |
| GET | `/api/categories/active` | Listar categorías activas |
| GET | `/api/categories/{id}` | Obtener categoría por ID |
| POST | `/api/categories` | Crear nueva categoría |
| PUT | `/api/categories/{id}` | Actualizar categoría |
| DELETE | `/api/categories/{id}` | Eliminar categoría |
| PATCH | `/api/categories/{id}/toggle-active` | Activar/desactivar categoría |

## 🔧 Ejecutar localmente

```bash
./gradlew bootRun
```

La API estará disponible en `http://localhost:8082`

## 🐳 Docker

```bash
# Construir imagen
docker build -t categories-service .

# Ejecutar contenedor
docker run -d -p 3002:8082 --name categories-api categories-service
```

## 📊 Ejemplo de datos

```json
{
  "name": "Electrónica",
  "description": "Productos electrónicos y tecnología",
  "active": true
}
```
