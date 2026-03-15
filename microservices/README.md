# Arquitectura de Microservicios - Primer Proyecto Full Stack

Este proyecto implementa una arquitectura de microservicios utilizando Spring Boot y SQLite, organizada como un monorepo.

## 📁 Estructura del Proyecto

```
microservices/
├── users-service/          # Microservicio de gestión de usuarios
│   ├── src/
│   │   └── main/
│   │       ├── java/
│   │       │   └── com/usersservice/
│   │       │       ├── UsersServiceApplication.java
│   │       │       └── users/
│   │       │           ├── controller/    # REST Controllers
│   │       │           ├── service/       # Lógica de negocio
│   │       │           ├── repository/    # Acceso a datos
│   │       │           ├── model/         # Entidades JPA
│   │       │           ├── dto/           # Data Transfer Objects
│   │       │           └── config/        # Configuración Spring
│   │       └── resources/
│   │           └── application.properties
│   ├── build.gradle
│   └── settings.gradle
│
└── tasks-service/          # Microservicio de gestión de tareas
    ├── src/
    │   └── main/
    │       ├── java/
    │       │   └── com/tasksservice/
    │       │       ├── TasksServiceApplication.java
    │       │       └── tasks/
    │       │           ├── controller/    # REST Controllers
    │       │           ├── service/       # Lógica de negocio
    │       │           ├── repository/    # Acceso a datos
    │       │           ├── model/         # Entidades JPA
    │       │           ├── dto/           # Data Transfer Objects
    │       │           └── config/        # Configuración Spring
    │       └── resources/
    │           └── application.properties
    ├── build.gradle
    └── settings.gradle
```

## 🚀 Microservicios

### 1. Users Service (Puerto 8081)

Gestiona toda la lógica relacionada con usuarios.

**Base de datos:** `users.db`

**Endpoints disponibles:**

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/v1/users` | Listar todos los usuarios |
| POST | `/api/v1/users` | Crear un nuevo usuario |
| GET | `/api/v1/users/{id}` | Obtener usuario por ID |
| PUT | `/api/v1/users/{id}` | Actualizar usuario |
| DELETE | `/api/v1/users/{id}` | Eliminar usuario |

**Ejemplo de petición:**
```bash
# Crear usuario
curl -X POST http://localhost:8081/api/v1/users \
  -H "Content-Type: application/json" \
  -d '{"username":"john_doe","email":"john@example.com"}'

# Listar usuarios
curl http://localhost:8081/api/v1/users
```

### 2. Tasks Service (Puerto 8082)

Gestiona tareas asociadas a usuarios.

**Base de datos:** `tasks.db`

**Endpoints disponibles:**

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/v1/tasks` | Listar todas las tareas |
| POST | `/api/v1/tasks` | Crear una nueva tarea |
| GET | `/api/v1/tasks/{id}` | Obtener tarea por ID |
| GET | `/api/v1/tasks/user/{userId}` | Obtener tareas de un usuario |
| GET | `/api/v1/tasks/search?title=` | Buscar tareas por título |
| PUT | `/api/v1/tasks/{id}` | Actualizar tarea completa |
| PATCH | `/api/v1/tasks/{id}/toggle` | Cambiar estado completado/pendiente |
| DELETE | `/api/v1/tasks/{id}` | Eliminar tarea |

**Ejemplo de petición:**
```bash
# Crear tarea
curl -X POST http://localhost:8082/api/v1/tasks \
  -H "Content-Type: application/json" \
  -d '{
    "title":"Aprender Spring Boot",
    "description":"Completar el tutorial de microservicios",
    "completed":false,
    "dueDate":"2024-12-31",
    "userId":1
  }'

# Listar tareas de un usuario
curl http://localhost:8082/api/v1/tasks/user/1

# Cambiar estado de completado
curl -X PATCH http://localhost:8082/api/v1/tasks/1/toggle
```

## 💻 Cómo ejecutar los microservicios

### Requisitos previos

- Java 25 o superior
- Gradle (incluido en el proyecto via wrapper)

### Opción 1: Ejecución individual

**Users Service:**
```bash
cd microservices/users-service
./gradlew bootRun
# o en Windows
gradlew.bat bootRun
```

**Tasks Service:**
```bash
cd microservices/tasks-service
./gradlew bootRun
# o en Windows
gradlew.bat bootRun
```

### Opción 2: Construcción de JARs

**Construir ambos servicios:**
```bash
# Users Service
cd microservices/users-service
./gradlew build

# Tasks Service
cd microservices/tasks-service
./gradlew build
```

**Ejecutar los JARs:**
```bash
# Users Service
java -jar microservices/users-service/build/libs/users-service-1.0.0.jar

# Tasks Service
java -jar microservices/tasks-service/build/libs/tasks-service-1.0.0.jar
```

## 🔧 Tecnologías utilizadas

- **Spring Boot 4.0.3** - Framework principal
- **Spring Data JPA** - Persistencia de datos
- **Spring Web** - REST APIs
- **Spring Security** - Seguridad (deshabilitada para desarrollo)
- **SQLite 3.51.2.0** - Base de datos embebida
- **Hibernate Community Dialects** - Soporte SQLite en JPA
- **Lombok** - Reducción de código boilerplate
- **SpringDoc OpenAPI** - Documentación de API

## 📊 Base de datos

Cada microservicio tiene su propia base de datos SQLite independiente:

- **users-service**: `users.db` - Almacena datos de usuarios
- **tasks-service**: `tasks.db` - Almacena datos de tareas

Las bases de datos se crean automáticamente al iniciar cada servicio.

## 🔒 Seguridad

**⚠️ IMPORTANTE:** La seguridad está deshabilitada para facilitar el desarrollo.

Para producción, debes:
1. Habilitar JWT o OAuth2 en ambos servicios
2. Implementar un API Gateway
3. Configurar CORS apropiadamente
4. Habilitar CSRF protection

## 📝 Documentación de API

Una vez ejecutando los servicios, puedes acceder a la documentación interactiva Swagger UI:

- **Users Service:** http://localhost:8081/swagger-ui.html
- **Tasks Service:** http://localhost:8082/swagger-ui.html

## 🧪 Testing

Ejecutar tests de cada servicio:

```bash
# Users Service
cd microservices/users-service
./gradlew test

# Tasks Service
cd microservices/tasks-service
./gradlew test
```

## 🏗️ Arquitectura

### Patrón de Capas

Cada microservicio sigue el patrón de arquitectura en capas:

1. **Controller Layer** - Maneja peticiones HTTP
2. **Service Layer** - Contiene lógica de negocio
3. **Repository Layer** - Acceso a datos con Spring Data JPA
4. **Model Layer** - Entidades JPA
5. **DTO Layer** - Objetos de transferencia de datos

### Ventajas de esta arquitectura

✅ **Independencia**: Cada servicio puede desplegarse y escalarse independientemente
✅ **Aislamiento de fallos**: Un error en un servicio no afecta a los demás
✅ **Tecnología flexible**: Cada servicio puede usar diferentes tecnologías
✅ **Desarrollo paralelo**: Equipos diferentes pueden trabajar en servicios diferentes
✅ **Base de datos por servicio**: Cada servicio gestiona sus propios datos

## 🔄 Próximos pasos

- [ ] Implementar API Gateway (Spring Cloud Gateway)
- [ ] Agregar Service Discovery (Eureka)
- [ ] Implementar Circuit Breaker (Resilience4j)
- [ ] Agregar Distributed Tracing (Sleuth + Zipkin)
- [ ] Configuración centralizada (Spring Cloud Config)
- [ ] Containerización con Docker
- [ ] Orquestación con Kubernetes

## 📖 Recursos adicionales

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Cloud Documentation](https://spring.io/projects/spring-cloud)
- [Microservices Patterns](https://microservices.io/patterns/index.html)

---

**Desarrollado con ❤️ usando Spring Boot y Arquitectura de Microservicios**
