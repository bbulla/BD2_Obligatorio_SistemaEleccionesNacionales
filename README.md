
# Sistema de Elecciones Nacionales 2025 - Uruguay

Proyecto para la materia de Base de Datos 2.
Sistema completo para la gestión y simulación de una elección nacional. Incluye frontend, backend y base de datos relacional.

## Estructura del Proyecto

BD2\_Obligatorio\_SistemaEleccionesNacionales/
├── backend/               # Proyecto Java Spring Boot
├── frontend/              # Proyecto React (Vite)
├── mysql-init


## Requisitos Previos

- Java 17+
- Node.js 18+
- MySQL 8+
- Maven
- NPM

## Base de Datos

### 1. Crear la base de datos

### 2. Ejecutar el script

Desde tu cliente o terminal MySQL ejecutar init.sql que se encuentra dentro de la carpeta mysql-init

## Backend (Spring Boot)

### 1. Ubicación

Carpeta: `backend/`

### 2. Configuración

Archivo: `src/main/resources/application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/elecciones
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
```

### 3. Ejecutar

Desde IntelliJ o terminal:

```bash
mvn spring-boot:run
```

El backend corre en: http://localhost:8080

---

## Frontend (React + Vite)

### 1. Ubicación

Carpeta: `frontend/`

### 2. Instalar dependencias

```bash
cd frontend
npm install
```

### 3. Ejecutar frontend

```bash
npm run dev
```

Frontend en: http://localhost:5173


## Accesos Especiales

### Presidente de Mesa

* PIN: `1234`

### Panel Administrativo

* URL: http://localhost:5173/admin
* Clave: `1234`

## Funcionalidades

* Registro de votantes
* Emisión de votos (válido, blanco, anulado)
* Control de acceso por PIN (presidente de mesa)
* Cierre de mesa y bloqueo del circuito
* Reportes de resultados por circuito, por departamento y a nivel nacional
* Panel administrativo para cargar elecciones, partidos, listas, candidatos, votantes, etc.




* Ana Belén Bulla
* María Belén de Oliveira
* Leonardo conde
