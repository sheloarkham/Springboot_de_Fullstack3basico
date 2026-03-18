# Microservicio de Productos

## Descripción
Microservicio REST para la gestión de productos. Corre en el puerto **8083**.

## Endpoints Disponibles

### 1. GET /api/v1/productos
**Descripción:** Retorna una lista de todos los productos en formato JSON.

**Ejemplo de uso:**
```bash
curl http://localhost:8083/api/v1/productos
```

**Respuesta:**
```json
[
  {
    "id": 1,
    "name": "Laptop Dell",
    "description": "Laptop de alto rendimiento",
    "price": 1200.00,
    "stock": 10,
    "category": "Tecnología",
    "createdAt": "2026-03-17T19:00:00"
  }
]
```

---

### 2. POST /api/v1/productos
**Descripción:** Permite agregar un nuevo producto.

**Ejemplo de uso:**
```bash
curl -X POST http://localhost:8083/api/v1/productos \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Laptop Dell",
    "description": "Laptop de alto rendimiento",
    "price": 1200.00,
    "stock": 10,
    "category": "Tecnología"
  }'
```

**Respuesta:**
```json
{
  "id": 1,
  "name": "Laptop Dell",
  "description": "Laptop de alto rendimiento",
  "price": 1200.00,
  "stock": 10,
  "category": "Tecnología",
  "createdAt": "2026-03-17T19:00:00"
}
```

---

### 3. GET /api/v1/productos/{id}
**Descripción:** Obtiene un producto específico por su ID.

**Ejemplo de uso:**
```bash
curl http://localhost:8083/api/v1/productos/1
```

---

### 4. PUT /api/v1/productos/{id}
**Descripción:** Actualiza completamente un producto existente.

**Ejemplo de uso:**
```bash
curl -X PUT http://localhost:8083/api/v1/productos/1 \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Laptop Dell XPS",
    "description": "Laptop actualizada",
    "price": 1500.00,
    "stock": 5,
    "category": "Tecnología"
  }'
```

---

### 5. PATCH /api/v1/productos/{id}
**Descripción:** Actualiza parcialmente un producto (solo los campos enviados).

**Ejemplo de uso:**
```bash
curl -X PATCH http://localhost:8083/api/v1/productos/1 \
  -H "Content-Type: application/json" \
  -d '{
    "price": 1300.00,
    "stock": 8
  }'
```

---

### 6. DELETE /api/v1/productos/{id}
**Descripción:** Elimina un producto.

**Ejemplo de uso:**
```bash
curl -X DELETE http://localhost:8083/api/v1/productos/1
```

---

## Cómo iniciar el microservicio

Desde la carpeta del microservicio:
```bash
cd microservices\products-service
gradlew.bat bootRun
```

O desde la raíz del proyecto:
```bash
cd c:\Users\Asus\Downloads\primer-proyecto-fs01-main\primer-proyecto-fs01-main\microservices\products-service && gradlew.bat bootRun
```

## Tecnologías
- Spring Boot 4.0.3
- Java 25
- SQLite Database
- JPA/Hibernate
- Lombok

## Base de Datos
El microservicio usa SQLite con una base de datos local llamada `products.db` que se crea automáticamente al iniciar el servicio.
