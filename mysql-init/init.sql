CREATE TABLE DEPARTAMENTO (
                              id INT PRIMARY KEY,
                              nombre VARCHAR(100)
);

INSERT INTO DEPARTAMENTO (id, nombre) VALUES (1, 'Montevideo'), (2, 'Canelones');

SELECT * FROM DEPARTAMENTO;

CREATE TABLE LOCALIDAD (
                           id INT PRIMARY KEY,
                           nombre VARCHAR(100),
                           id_departamento INT,
                           FOREIGN KEY (id_departamento) REFERENCES DEPARTAMENTO(id)
);

INSERT INTO LOCALIDAD (id, nombre, id_departamento) VALUES
                                                        (1, 'Ciudad Vieja', 1),
                                                        (2, 'Pando', 2);

CREATE TABLE ESTABLECIMIENTO (
                                 id INT PRIMARY KEY,
                                 nombre VARCHAR(100),
                                 tipo VARCHAR(50),
                                 id_localidad INT,
                                 FOREIGN KEY (id_localidad) REFERENCES LOCALIDAD(id)
);

INSERT INTO ESTABLECIMIENTO (id, nombre, tipo, id_localidad) VALUES
                                                                 (1, 'Escuela 1', 'Escuela', 1),
                                                                 (2, 'Liceo 2', 'Liceo', 2);


CREATE TABLE CIRCUITO (
                          id INT PRIMARY KEY,
                          id_establecimiento INT,
                          es_accesible SMALLINT,
                          fecha DATE,
                          hora TIME,
                          cerrado TINYINT(1) DEFAULT 0,
                          descripcion VARCHAR(200),
                          FOREIGN KEY (id_establecimiento) REFERENCES ESTABLECIMIENTO(id)
);

INSERT INTO CIRCUITO (id, id_establecimiento, es_accesible, fecha, hora, descripcion) VALUES
                                                                                          (1, 1, 1, '2025-06-11', '08:00:00', 'Circuito Escuela 1'),
                                                                                          (2, 2, 0, '2025-06-11', '08:00:00', 'Circuito Liceo 2');


CREATE TABLE IF NOT EXISTS LOCALIDAD (
                                         id INT PRIMARY KEY,
                                         nombre VARCHAR(100),
    id_departamento INT,
    FOREIGN KEY (id_departamento) REFERENCES DEPARTAMENTO(id)
    );



CREATE TABLE COMISARIA (
                           id INT PRIMARY KEY,
                           nombre VARCHAR(100),
                           direccion VARCHAR(200)
);

INSERT INTO COMISARIA (id, nombre, direccion) VALUES
                                                  (1, 'Comisaría 1', 'Av. Principal 123'),
                                                  (2, 'Comisaría 2', 'Calle Falsa 456');



CREATE TABLE POLICIA (
                         nro_policia INT PRIMARY KEY,
                         CC CHAR(8),
                         id_comisaria INT,
                         id_establecimiento INT,
                         FOREIGN KEY (id_comisaria) REFERENCES COMISARIA(id),
                         FOREIGN KEY (id_establecimiento) REFERENCES ESTABLECIMIENTO(id)
);

INSERT INTO POLICIA (nro_policia, CC, id_comisaria, id_establecimiento) VALUES
                                                                            (101, '12345678', 1, 1),
                                                                            (102, '87654321', 2, 2);


CREATE TABLE PARTIDO (
                         id INT PRIMARY KEY,
                         descripcion VARCHAR(200),
                         direccion VARCHAR(200)
);

INSERT INTO PARTIDO (id, descripcion, direccion) VALUES
                                                     (1, 'Partido A', 'Av. Libertador 123'),
                                                     (2, 'Partido B', 'Calle Democracia 456');


CREATE TABLE HABILITADO (
                            CC CHAR(8) PRIMARY KEY,
                            CI CHAR(8),
                            nombres VARCHAR(100),
                            apellidos VARCHAR(100),
                            f_nacimiento DATE,
                            fecha_voto DATE,
                            posicion INT,
                            id_circuito INT,
                            id_partido INT,
                            FOREIGN KEY (id_circuito) REFERENCES CIRCUITO(id),
                            FOREIGN KEY (id_partido) REFERENCES PARTIDO(id)
);

INSERT INTO HABILITADO (CC, CI, nombres, apellidos, f_nacimiento, fecha_voto, posicion, id_circuito, id_partido) VALUES
                                                                                                                     ('11112222', '12345678', 'Juan', 'Pérez', '1990-05-01', NULL, NULL, 1, 1),
                                                                                                                     ('33334444', '87654321', 'Lucía', 'González', '1988-11-22', NULL, NULL, 2, 2);


CREATE TABLE CANDIDATO (
                           CC CHAR(8) PRIMARY KEY,
                           nro_posicion INT,
                           rol VARCHAR(50),
                           FOREIGN KEY (CC) REFERENCES HABILITADO(CC)
);

INSERT INTO CANDIDATO (CC, nro_posicion, rol) VALUES
                                                  ('11112222', 1, 'Intendente'),
                                                  ('33334444', 2, 'Edil');


CREATE TABLE MIEMBRO_MESA (
                              CC CHAR(8) PRIMARY KEY,
                              organismo VARCHAR(100),
                              rol VARCHAR(50),
                              id_circuito INT,
                              FOREIGN KEY (CC) REFERENCES HABILITADO(CC),
                              FOREIGN KEY (id_circuito) REFERENCES CIRCUITO(id)
);

INSERT INTO MIEMBRO_MESA (CC, organismo, rol, id_circuito) VALUES
                                                               ('11112222', 'Ministerio de Educación', 'Presidente', 1),
                                                               ('33334444', 'Ministerio del Interior', 'Vocal', 2);

CREATE TABLE ELECCION (
                          id INT PRIMARY KEY,
                          tipo VARCHAR(50),
                          fecha DATE,
                          descripcion VARCHAR(200)
);

INSERT INTO ELECCION (id, tipo, fecha, descripcion) VALUES
                                                        (1, 'Presidencial', '2025-06-11', 'Elección nacional presidencial 2025'),
                                                        (2, 'Referéndum', '2025-06-11', 'Reforma constitucional');


CREATE TABLE LISTA (
                       id INT PRIMARY KEY,
                       lema VARCHAR(100),
                       nro_lista INT,
                       id_eleccion INT NOT NULL,
                       id_partido INT NOT NULL,
                       FOREIGN KEY (id_eleccion) REFERENCES ELECCION(id),
                       FOREIGN KEY (id_partido) REFERENCES PARTIDO(id)
);

INSERT INTO LISTA (id, lema, nro_lista, id_eleccion) VALUES
                                                         (1, 'Lista 123 Partido A', 123, 1),
                                                         (2, 'Lista 456 Partido B', 456, 1);

CREATE TABLE PAPELETA (
                          id INT PRIMARY KEY,
                          descripcion VARCHAR(200)
);

INSERT INTO PAPELETA (id, descripcion) VALUES
                                           (1, 'Papeleta por el SÍ'),
                                           (2, 'Papeleta por el NO');


CREATE TABLE VOTO (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      id_circuito INT,
                      id_eleccion INT,
                      es_observado SMALLINT,
                      tipo VARCHAR(50),
                      FOREIGN KEY (id_circuito) REFERENCES CIRCUITO(id),
                      FOREIGN KEY (id_eleccion) REFERENCES ELECCION(id)
);

CREATE TABLE VOTO_LISTA (
                            id_voto INT PRIMARY KEY,
                            id_lista INT,
                            FOREIGN KEY (id_voto) REFERENCES VOTO(id),
                            FOREIGN KEY (id_lista) REFERENCES LISTA(id)
);

INSERT INTO VOTO_LISTA (id_voto, id_lista) VALUES
                                               (1, 1),
                                               (2, 2);


CREATE TABLE VOTO_PAPELETA (
                               id_voto INT,
                               id_papeleta INT,
                               PRIMARY KEY (id_voto, id_papeleta),
                               FOREIGN KEY (id_voto) REFERENCES VOTO(id),
                               FOREIGN KEY (id_papeleta) REFERENCES PAPELETA(id)
);

INSERT INTO VOTO_PAPELETA (id_voto, id_papeleta) VALUES
                                                     (3, 1),
                                                     (3, 2);


CREATE TABLE VOTO_REGISTRO (
                               id INT AUTO_INCREMENT PRIMARY KEY,
                               cc CHAR(8),
                               id_eleccion INT,
                               UNIQUE (cc, id_eleccion)
);