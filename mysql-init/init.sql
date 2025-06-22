-- =========================
-- SCRIPT DE CREACIÓN DE TABLAS
-- Elecciones Nacionales 2025 - Uruguay
-- =========================

CREATE TABLE ELECCION (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          tipo VARCHAR(50),
                          fecha DATE,
                          descripcion VARCHAR(255)
);

CREATE TABLE PARTIDO (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         descripcion VARCHAR(100),
                         direccion VARCHAR(100)
);

CREATE TABLE LISTA (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       lema VARCHAR(100),
                       nro_lista INT,
                       id_eleccion INT,
                       id_partido INT,
                       FOREIGN KEY (id_eleccion) REFERENCES ELECCION(id),
                       FOREIGN KEY (id_partido) REFERENCES PARTIDO(id)
);

CREATE TABLE DEPARTAMENTO (
                              id INT AUTO_INCREMENT PRIMARY KEY,
                              nombre VARCHAR(100)
);

CREATE TABLE LOCALIDAD (
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           nombre VARCHAR(100),
                           id_departamento INT,
                           FOREIGN KEY (id_departamento) REFERENCES DEPARTAMENTO(id)
);

CREATE TABLE COMISARIA (
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           nombre VARCHAR(100),
                           direccion VARCHAR(100),
                           calle VARCHAR(100),
                           numero INT
);

CREATE TABLE ESTABLECIMIENTO (
                                 id INT AUTO_INCREMENT PRIMARY KEY,
                                 nombre VARCHAR(100),
                                 tipo VARCHAR(50),
                                 id_localidad INT,
                                 FOREIGN KEY (id_localidad) REFERENCES LOCALIDAD(id)
);

CREATE TABLE CIRCUITO (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          nro_circuito INT,
                          es_accesible BOOLEAN,
                          id_establecimiento INT,
                          cerrado BOOLEAN,
                          FOREIGN KEY (id_establecimiento) REFERENCES ESTABLECIMIENTO(id)
);

CREATE TABLE HABILITADO (
                            cc VARCHAR(20) PRIMARY KEY,
                            ci VARCHAR(20),
                            nombres VARCHAR(100),
                            apellidos VARCHAR(100),
                            f_nacimiento DATE,
                            id_circuito INT,
                            FOREIGN KEY (id_circuito) REFERENCES CIRCUITO(id)
);

CREATE TABLE PAPELETA (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          descripcion VARCHAR(255),
                          id_eleccion INT,
                          FOREIGN KEY (id_eleccion) REFERENCES ELECCION(id)
);

CREATE TABLE CANDIDATO (
                           cc VARCHAR(20) PRIMARY KEY,
                           nro_posicion INT,
                           rol VARCHAR(50),
                           id_lista INT,
                           FOREIGN KEY (cc) REFERENCES HABILITADO(cc),
                           FOREIGN KEY (id_lista) REFERENCES LISTA(id)
);

CREATE TABLE MIEMBRO_MESA (
                              cc VARCHAR(20) PRIMARY KEY,
                              organismo VARCHAR(100),
                              rol VARCHAR(50),
                              id_circuito INT,
                              FOREIGN KEY (cc) REFERENCES HABILITADO(cc),
                              FOREIGN KEY (id_circuito) REFERENCES CIRCUITO(id)
);

CREATE TABLE POLICIA (
                         cc VARCHAR(20) PRIMARY KEY,
                         nro_policia VARCHAR(20),
                         id_comisaria INT,
                         id_establecimiento INT,
                         FOREIGN KEY (cc) REFERENCES HABILITADO(cc),
                         FOREIGN KEY (id_comisaria) REFERENCES COMISARIA(id),
                         FOREIGN KEY (id_establecimiento) REFERENCES ESTABLECIMIENTO(id)
);

CREATE TABLE VOTO (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      id_circuito INT,
                      id_eleccion INT,
                      es_observado BOOLEAN,
                      tipo VARCHAR(50),
                      FOREIGN KEY (id_circuito) REFERENCES CIRCUITO(id),
                      FOREIGN KEY (id_eleccion) REFERENCES ELECCION(id)
);

CREATE TABLE VOTO_LISTA (
                            id_voto INT,
                            id_lista INT,
                            PRIMARY KEY (id_voto, id_lista),
                            FOREIGN KEY (id_voto) REFERENCES VOTO(id),
                            FOREIGN KEY (id_lista) REFERENCES LISTA(id)
);

CREATE TABLE VOTO_PAPELETA (
                               id_voto INT,
                               id_papeleta INT,
                               PRIMARY KEY (id_voto, id_papeleta),
                               FOREIGN KEY (id_voto) REFERENCES VOTO(id),
                               FOREIGN KEY (id_papeleta) REFERENCES PAPELETA(id)
);

CREATE TABLE VOTO_REGISTRO (
                               cc VARCHAR(20),
                               id_eleccion INT,
                               id_circuito INT,
                               PRIMARY KEY (cc, id_eleccion),
                               FOREIGN KEY (cc) REFERENCES HABILITADO(cc),
                               FOREIGN KEY (id_eleccion) REFERENCES ELECCION(id),
                               FOREIGN KEY (id_circuito) REFERENCES CIRCUITO(id)
);


-- =============================
-- INIT SCRIPT - DATOS
-- =============================

-- DEPARTAMENTOS
INSERT INTO DEPARTAMENTO (id, nombre) VALUES
                                          (1, 'Montevideo'), (2, 'Canelones');

-- LOCALIDADES
INSERT INTO LOCALIDAD (id, nombre, id_departamento) VALUES
                                                        (1, 'Centro', 1), (2, 'Ciudad de la Costa', 2);

-- COMISARIAS
INSERT INTO COMISARIA (id, nombre, direccion, calle, numero) VALUES
                                                                 (1, 'Comisaría 1', 'Av. Principal 123', 'Av. Principal', 123),
                                                                 (2, 'Comisaría 2', 'Calle Falsa 456', 'Calle Falsa', 456);

-- ESTABLECIMIENTOS
INSERT INTO ESTABLECIMIENTO (id, nombre, tipo, id_localidad) VALUES
                                                                 (1, 'Escuela 123', 'Escuela', 1),
                                                                 (2, 'Escuela 122', 'Escuela', 1),
                                                                 (3, 'Colegio Sagrada Familia', 'Colegio', 2);

-- CIRCUITOS
INSERT INTO CIRCUITO (id, nro_circuito, es_accesible, id_establecimiento, cerrado) VALUES
                                                                                       (1, 34, TRUE, 1, FALSE),
                                                                                       (2, 35, FALSE, 1, FALSE),
                                                                                       (3, 34, TRUE, 2, FALSE),
                                                                                       (4, 35, FALSE, 2, FALSE),
                                                                                       (5, 34, TRUE, 3, FALSE),
                                                                                       (6, 35, FALSE, 3, FALSE);

-- ELECCION
INSERT INTO ELECCION (id, tipo, fecha, descripcion) VALUES
    (1, 'Nacional', '2025-10-27', 'Elección Nacional 2025');

-- PARTIDOS
INSERT INTO PARTIDO (id, descripcion, direccion) VALUES
                                                     (1, 'Partido Colorado', 'Av. Libertador 1111'),
                                                     (2, 'Frente Amplio', 'Av. Italia 2222'),
                                                     (3, 'Partido Nacional', '18 de Julio 3333'),
                                                     (4, 'Partido Independiente', 'Bvar. Artigas 4444');

-- LISTAS
INSERT INTO LISTA (id, lema, nro_lista, id_eleccion, id_partido) VALUES
                                                                     (1, 'Lista 1 - Partido Nacional', 1, 1, 3),
                                                                     (2, 'Lista 99 - Partido Nacional', 99, 1, 3),
                                                                     (3, 'Lista 12 - Frente Amplio', 12, 1, 2),
                                                                     (4, 'Lista 1 - Frente Amplio', 1, 1, 2);

-- PAPELETAS
INSERT INTO PAPELETA (id, descripcion, id_eleccion) VALUES
                                                        (1, 'Papeleta A', 1),
                                                        (2, 'Papeleta B', 1);

-- HABILITADOS
INSERT INTO HABILITADO (cc, ci, nombres, apellidos, f_nacimiento, id_circuito) VALUES
                                                                                   ('AIA5443', '41234567', 'Juan', 'Pérez', '1990-01-01', 1),
                                                                                   ('BJB1234', '42345678', 'Ana', 'Gómez', '1992-02-02', 3),
                                                                                   ('CKC9876', '43456789', 'Luis', 'Rodríguez', '1988-03-03', 5);

-- POLICIAS
INSERT INTO POLICIA (cc, nro_policia, id_comisaria, id_establecimiento) VALUES
    ('AIA5443', 'P001', 1, 1);

-- MIEMBROS DE MESA
INSERT INTO MIEMBRO_MESA (cc, organismo, rol, id_circuito) VALUES
    ('BJB1234', 'Corte Electoral', 'Presidente', 3);

-- CANDIDATOS
INSERT INTO CANDIDATO (cc, nro_posicion, rol, id_lista) VALUES
    ('CKC9876', 1, 'Presidenciable', 3);