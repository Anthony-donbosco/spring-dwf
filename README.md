# 🏨 Sistema Integral de Gestión y Reservas Hoteleras — Grand Royal Hotels & Resorts

[![Java](https://img.shields.io/badge/Java-17%2B-orange.svg?style=flat-square&logo=openjdk)](https://www.oracle.com/java/)

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x%20%2F%204.x-brightgreen.svg?style=flat-square&logo=springboot)](https://spring.io/projects/spring-boot)

[![MySQL](https://img.shields.io/badge/MySQL-8.0%2B-blue.svg?style=flat-square&logo=mysql)](https://www.mysql.com/)

[![License](https://img.shields.io/badge/License-Academic%20%2F%20UDB-lightgrey.svg?style=flat-square)]()

Proyecto de Cátedra — **Fase 1: Especificación Técnica, Diseño UI/UX y Modelo Relacional**  
**Asignatura:** Desarrollo de Aplicaciones Web Framework (DWF)  
**Docente:** Ing. Mario Alvarado  
**Institución:** Universidad Don Bosco (UDB) — El Salvador  

---

## 1. Integrantes del Equipo y Asignación de Roles

El proyecto se gestiona bajo el marco ágil **Scrum**, distribuyendo responsabilidades para garantizar la calidad en arquitectura, persistencia, interfaz y documentación:

| Nombre Completo | Carnet / Código | Rol en el Proyecto | Responsabilidades Principales |
|---|---|---|---|
| **Anthony Alexander Monterrosa Rivera** | `MR252990` | **Líder Frontend / Diseñador UI/UX / Bases de Datos** | Diseño de maquetas interactivas, arquitectura relacional 3FN y diccionario de datos en MySQL. |
| **Elmer Eduardo Rivas Avilés** | `RA252958` | **Arquitecto Backend / Integrador Spring** | Configuración de Spring Boot, mapeo objeto-relacional (ORM JPA/Hibernate) y lógica de negocio. |
| **Edwin Gabriel Medrano Yanes** | `MY252997` | **Scrum Master / Analista de Documentación** | Gestión de ceremonias ágiles, tablero Kanban en Trello, especificación de endpoints REST y aseguramiento de rúbrica. |

---

## 2. Enlaces de Gestión Ágil y Documentación

* **Tablero Kanban en Trello:** [Ver Tablero ](https://trello.com/invite/b/6a93c946b1ea23aaaa812928/ATTI3361a5f9f00d77a44d52903660832ad750ECD503/dwf-sistema-de-reservas-hoteleras)

* **Repositorio Oficial en GitHub:** [https://github.com/Anthony-donbosco/spring-dwf]
* **Documento Técnico Oficial (Fase 1 PDF):** Disponible en la entrega del Aula Digital.

---

## 3. Descripción y Objetivos del Proyecto

### 3.1. Objetivo General
Desarrollar una solución integral para la gestión y reserva de habitaciones hoteleras mediante una **API REST desacoplada construida en Java con Spring Boot**, respaldada por una base de datos relacional normalizada en **MySQL 8.0**, diseñada para ser consumida de forma reactiva por interfaces web modernas.

### 3.2. Alcance Funcional (Fase 1)
* **Motor de Búsqueda y Disponibilidad:** Búsqueda en tiempo real por hotel/sucursal, rango de fechas de check-in / check-out, y conteo de adultos/niños.
* **Catálogo y Detalle de Habitaciones:** Filtrado por precio, capacidad, comodidades y servicios adicionales contratables (desayuno buffet, traslados, spa).
* **Registro y Gestión de Huéspedes:** Captura validada de titular (DUI/Pasaporte, contacto, peticiones especiales).
* **Pasarela de Pre-Reserva y Pagos:** Cálculo transparente de subtotal de noches, extras, 13% de IVA y métodos de cobro (tarjeta, transferencia, recepción).
* **Emisión de Voucher Digital:** Comprobante con localizador alfanumérico único (`#RES-2026-XXXX`) y código QR para Check-in express.
* **Autogestión de Reservas y Reclamos:** Reprogramación de fechas, anulación con política de reembolso y registro de tickets de incidencia.
* **Dashboard Administrativo:** Panel de control de KPIs (tasa de ocupación, ingresos mensuales, movimientos del día).

---

## 4. Arquitectura de Software y Stack Tecnológico

El backend sigue un patrón de **Arquitectura en Capas (Layered Architecture)** bajo principios **SOA (Service-Oriented Architecture)**:

```text
┌─────────────────────────────────────────────────────────────┐
│                    CLIENTE FRONTEND                         │
└──────────────────────────────┬──────────────────────────────┘
                               │ HTTP / JSON (REST API)
┌──────────────────────────────▼──────────────────────────────┐
│                   CAPA DE CONTROLADORES                     │
│                (@RestController / @CrossOrigin)             │
├─────────────────────────────────────────────────────────────┤
│                    CAPA DE SERVICIOS                        │
│             (@Service / @Transactional / DTOs)              │
├─────────────────────────────────────────────────────────────┤
│                   CAPA DE REPOSITORIOS                      │
│            (Spring Data JPA / JpaRepository / JPQL)         │
├─────────────────────────────────────────────────────────────┤
│                   CAPA DE PERSISTENCIA                      │
│                  (@Entity / Hibernate ORM)                  │
└──────────────────────────────┬──────────────────────────────┘
                               │ JDBC (Puerto 3306)
┌──────────────────────────────▼──────────────────────────────┐
│                    BASE DE DATOS RELACIONAL                 │
│                 (MySQL 8.0+ / Motor InnoDB)                 │
└─────────────────────────────────────────────────────────────┘
```

### Tecnologías Empleadas:
* **Lenguaje:** Java 17 LTS
* **Framework:** Spring Boot (Spring Web, Spring Data JPA, Hibernate, Bean Validation)
* **Persistencia:** MySQL 8.0
* **Utilidades:** Project Lombok, Maven Wrapper (`mvnw`)
* **Diseño UI:** Figma, Bootstrap 5, Bootstrap Icons

---

## 5. Modelo Relacional de Base de Datos

La base de datos se encuentra normalizada en **Tercera Forma Normal (3FN)** con claves foráneas e integridad referencial estricta:

```text
sucursales_hotel (1) ────< (N) habitaciones (N) >──── (1) tipos_habitacion
                                   │
                                   ▼ (1)
huespedes (1) ───────────< (N) reservas (N) >──────── (1) politicas_cancelacion
                                │    │
         ┌──────────────────────┘    └───────────────────────┐
         ▼ (1)                                               ▼ (1)
reserva_servicios (N) >── (1) servicios_adicionales       pagos (N)
         │                                                   │
cancelaciones_reembolsos (1:1)                   reclamos_solicitudes (N)
```

---

## 6. Guía de Instalación y Ejecución Local

### Prerrequisitos:
1. **Java JDK 17 o superior** instalado y configurado en el `PATH` (`java -version`).
2. **MySQL Server 8.0+** en ejecución en el puerto `3306`.
3. **IDE recomendado:** IntelliJ IDEA Ultimate / VS Code / Eclipse STS.

### Paso 1: Clonar el Repositorio
```bash
git clone https://github.com/Anthony-donbosco/spring-dwf.git
cd spring-dwf
```

### Paso 2: Crear la Base de Datos en MySQL
Ejecutar en MySQL o terminal:
```sql
CREATE DATABASE hotel_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

### Paso 3: Configurar Credenciales
Verificar o editar el archivo `src/main/resources/application.properties`:
```properties
server.port=8080

spring.datasource.url=jdbc:mysql://localhost:3306/hotel_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=password_prueba_100_real
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

### Paso 4: Compilar y Ejecutar
Usando el Maven Wrapper incluido en el proyecto:
* En Windows:
  ```powershell
  .\mvnw spring-boot:run
  ```
* En Linux / macOS:
  ```bash
  ./mvnw spring-boot:run
  ```
La API estará escuchando en `http://localhost:8080`.

---

## 7. Especificación de Endpoints REST Principales

| Verbo | Endpoint | Descripción | Acceso |
|---|---|---|---|
| `GET` | `/api/habitaciones/disponibles` | Consulta disponibilidad por hotel, fechas y huéspedes | Público |
| `GET` | `/api/habitaciones/{id}` | Obtiene detalle y fotos de una habitación específica | Público |
| `GET` | `/api/servicios-adicionales` | Listado de servicios extras vigentes | Público |
| `POST` | `/api/reservas` | Registra y confirma una nueva reserva hotelera | Huésped |
| `GET` | `/api/reservas/voucher/{codigo}` | Consulta y descarga de voucher por localizador | Huésped |
| `PUT` | `/api/reservas/{id}/modificar` | Reprograma fechas de estancia | Huésped |
| `POST` | `/api/reservas/{id}/cancelar` | Solicita anulación y calcula reembolso | Huésped |
| `POST` | `/api/pagos/procesar` | Registra la transacción financiera de la reserva | Sistema / Pasarela |
| `POST` | `/api/reclamos` | Crea un ticket de atención al cliente | Huésped |
| `GET` | `/api/admin/dashboard/kpis` | Métricas de ocupación, ingresos y reportes | Administrador |

---

## 8. Prototipos de Interfaz de Usuario (Mockups)

Los 10+ prototipos de alta fidelidad fueron creados y maquetados con **Bootstrap 5** y se encuentran disponibles en la carpeta `/pantallas`:

1. **`home_busqueda`**: Página de inicio, banners y formulario flotante de búsqueda.
2. **`catalogo_resultados`**: Catálogo de habitaciones con filtros laterales de precio y comodidades.
3. **`detalle_habitacion_servicios`**: Ficha técnica de habitación y selector dinámico de servicios extras.
4. **`registro_huesped`**: Formulario de captura y validación de datos del huésped titular.
5. **`resumen_pre_reserva`**: Resumen de cotización, cálculo de impuestos (13% IVA) y política de cancelación.
6. **`pasarela_pago`**: Pasarela con selección de tarjeta, transferencia o pago en recepción.
7. **`voucher_confirmacion`**: Comprobante de confirmación con código localizador alfanumérico y código QR.
8. **`autogestion_cancelacion`**: Portal de autogestión para modificación y cancelación con reembolso.
9. **`reclamos_solicitudes`**: Centro de atención al cliente y seguimiento de tickets de incidencia.
10. **`dashboard_admin`**: Panel administrativo de analítica, ocupación y control diario.
11. **`perfil_usuario`**: Panel de usuario (Mi Cuenta), historial de estadías y puntos de fidelidad.

---

## 9. Licencia y Criterios de Evaluación
Desarrollado para la evaluación de **Primera Fase del Proyecto DWF 2026** — Escuela de Computación, Universidad Don Bosco.
