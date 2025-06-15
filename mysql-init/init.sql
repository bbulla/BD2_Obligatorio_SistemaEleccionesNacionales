-- DROP DATABASE elecciones IF EXISTS; -- opcional
CREATE DATABASE IF NOT EXISTS elecciones;
USE elecciones;

-- Tabla: DEPARTAMENTO
CREATE TABLE IF NOT EXISTS DEPARTAMENTO (
                              id INT PRIMARY KEY,
                              nombre VARCHAR(100)
);

INSERT INTO DEPARTAMENTO (id, nombre) VALUES
                                          (1, 'Montevideo'),
                                          (2, 'Canelones');

-- Tabla: LOCALIDAD
CREATE TABLE IF NOT EXISTS LOCALIDAD (
                           id INT PRIMARY KEY,
                           nombre VARCHAR(100),
                           id_departamento INT,
                           FOREIGN KEY (id_departamento) REFERENCES DEPARTAMENTO(id)
);

INSERT INTO LOCALIDAD (id, nombre, id_departamento) VALUES
                                                        (1, 'Ciudad Vieja', 1),
                                                        (2, 'Pando', 2);

-- Tabla: ESTABLECIMIENTO
CREATE TABLE IF NOT EXISTS ESTABLECIMIENTO (
                                 id INT PRIMARY KEY,
                                 nombre VARCHAR(100),
                                 tipo VARCHAR(50),
                                 id_localidad INT,
                                 FOREIGN KEY (id_localidad) REFERENCES LOCALIDAD(id)
);

INSERT INTO ESTABLECIMIENTO (id, nombre, tipo, id_localidad) VALUES
                                                                 (1, 'Escuela 1', 'Escuela', 1),
                                                                 (2, 'Liceo 2', 'Liceo', 2);

-- Tabla: CIRCUITO
CREATE TABLE IF NOT EXISTS CIRCUITO (
                          id INT PRIMARY KEY,
                          id_establecimiento INT,
                          es_accesible SMALLINT,
                          fecha DATE,
                          hora TIME,
                          descripcion VARCHAR(200),
                          FOREIGN KEY (id_establecimiento) REFERENCES ESTABLECIMIENTO(id)
);

INSERT INTO CIRCUITO (id, id_establecimiento, es_accesible, fecha, hora, descripcion) VALUES
                                                                                          (1, 1, 1, '2025-06-11', '08:00:00', 'Circuito Escuela 1'),
                                                                                          (2, 2, 0, '2025-06-11', '08:00:00', 'Circuito Liceo 2');
