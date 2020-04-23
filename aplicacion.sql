--Creacion de la bd
create database aplicacion
------------------------------------------------
--Creacion de las tablas 
create table carreras(
id_carrera character varying not null,
nombre character varying,
CONSTRAINT pk_idcarrera PRIMARY KEY (id_carrera)
);


create table tipos_usuarios(
id_tipos_usuario character varying not null,
tipo character varying,
CONSTRAINT pk_id_tipos_usuario PRIMARY KEY (id_tipos_usuario)
);

create table usuarios(
matricula character varying not null,
id_tipos_usuario character varying not null,
id_carrera character varying,
nombre character varying,
apellido_paterno character varying,
apellido_materno character varying,
contrasena character varying,
CONSTRAINT pk_matricula PRIMARY KEY (matricula),
CONSTRAINT fk_id_tipos_usuario FOREIGN KEY (id_tipos_usuario) REFERENCES tipos_usuarios (id_tipos_usuario),
CONSTRAINT fk_id_carrera FOREIGN KEY (id_carrera) REFERENCES carreras (id_carrera)
);

create table tipos_equipos(
id_tipos_quipo character varying not null,
nombre character varying,
entrada character varying,
CONSTRAINT pk_id_tipos_quipo PRIMARY KEY (id_tipos_quipo)
);

create table equipos(
id_equipo character varying not null,
id_tipos_quipo character varying not null,
serial character varying,
CONSTRAINT pk_id_equipo PRIMARY KEY (id_equipo),
CONSTRAINT fk_id_tipos_quipo FOREIGN KEY (id_tipos_quipo) REFERENCES tipos_equipos (id_tipos_quipo)
);

create table lugares(
id_lugar character varying not null,
edificio character varying,
aula character varying,
CONSTRAINT pk_id_lugar PRIMARY KEY (id_lugar)
);

create table apartados(
id_apartado SERIAL,
matricula character varying not null,
id_equipo character varying not null,
id_lugar character varying not null,
grupo character varying,
fecha character varying,
hora_inicio character varying,
hora_final character varying,
CONSTRAINT pk_id_apartado PRIMARY KEY (id_apartado),
CONSTRAINT matricula FOREIGN KEY (matricula) REFERENCES usuarios (matricula),
CONSTRAINT fk_id_equipo FOREIGN KEY (id_equipo) REFERENCES equipos (id_equipo),
CONSTRAINT fk_id_lugar FOREIGN KEY (id_lugar) REFERENCES lugares (id_lugar)
);


-------------------------------------------------------------------------------
--Agregar carreras 
INSERT INTO carreras (id_carrera, nombre) VALUES ('1', 'Contaduria');
INSERT INTO carreras (id_carrera, nombre) VALUES ('2', 'Administracion');
INSERT INTO carreras (id_carrera, nombre) VALUES ('3', 'Gestion');
INSERT INTO carreras (id_carrera, nombre) VALUES ('4', 'Sistemas');
INSERT INTO carreras (id_carrera, nombre) VALUES ('5', 'Informatica');
INSERT INTO carreras (id_carrera, nombre) VALUES ('6', 'Ing Software');
--Agregar tipos de usuario 
INSERT INTO tipos_usuarios (id_tipos_usuario, tipo) VALUES ('1', 'Academico');
INSERT INTO tipos_usuarios (id_tipos_usuario, tipo) VALUES ('2', 'Estudiante');
--Agregar usuarios
INSERT INTO usuarios (matricula, id_tipos_usuario, id_carrera, nombre, apellido_paterno, apellido_materno, contrasena) VALUES ('S17004061', '2', '6', 'Adrian', 'Sanchez', 'Contreras', '12345');
INSERT INTO usuarios (matricula, id_tipos_usuario, id_carrera, nombre, apellido_paterno, apellido_materno, contrasena) VALUES ('P58285658', '1', '6', 'Roberto', 'Campos', 'Novas', 'profe99');
--Agregar tipos de equipos
INSERT INTO tipos_equipos (id_tipos_quipo, nombre, entrada) VALUES ('1','Proyector','HDMI');
INSERT INTO tipos_equipos (id_tipos_quipo, nombre, entrada) VALUES ('2','Proyector','VGA');
--Agregar equipos 
INSERT INTO equipos (id_equipo, id_tipos_quipo,serial) VALUES ('1','1','SDF123');
INSERT INTO equipos (id_equipo, id_tipos_quipo,serial) VALUES ('2','2','SDF124');
--Agregar lugares
INSERT INTO lugares (id_lugar, edificio, aula) VALUES ('1', '1','1');
INSERT INTO lugares (id_lugar, edificio, aula) VALUES ('2', '1','2');
INSERT INTO lugares (id_lugar, edificio, aula) VALUES ('3', '1','3');
INSERT INTO lugares (id_lugar, edificio, aula) VALUES ('4', '1','4');
INSERT INTO lugares (id_lugar, edificio, aula) VALUES ('5', '1','5');
INSERT INTO lugares (id_lugar, edificio, aula) VALUES ('6', '1','6');
INSERT INTO lugares (id_lugar, edificio, aula) VALUES ('7', '1','7');
INSERT INTO lugares (id_lugar, edificio, aula) VALUES ('8', '1','8');
INSERT INTO lugares (id_lugar, edificio, aula) VALUES ('9', '1','9');
INSERT INTO lugares (id_lugar, edificio, aula) VALUES ('10', '1','10');
INSERT INTO lugares (id_lugar, edificio, aula) VALUES ('11', '1','11');
INSERT INTO lugares (id_lugar, edificio, aula) VALUES ('12', '1','12');
INSERT INTO lugares (id_lugar, edificio, aula) VALUES ('13', '1','13');
INSERT INTO lugares (id_lugar, edificio, aula) VALUES ('14', '1','14');
INSERT INTO lugares (id_lugar, edificio, aula) VALUES ('15', '1','15');
INSERT INTO lugares (id_lugar, edificio, aula) VALUES ('16', '1','16');
INSERT INTO lugares (id_lugar, edificio, aula) VALUES ('17', '2','1');
INSERT INTO lugares (id_lugar, edificio, aula) VALUES ('18', '2','2');
INSERT INTO lugares (id_lugar, edificio, aula) VALUES ('19', '2','3');
INSERT INTO lugares (id_lugar, edificio, aula) VALUES ('20', '2','4');
INSERT INTO lugares (id_lugar, edificio, aula) VALUES ('21', '2','5');
INSERT INTO lugares (id_lugar, edificio, aula) VALUES ('22', '2','6');
INSERT INTO lugares (id_lugar, edificio, aula) VALUES ('23', '2','7');
INSERT INTO lugares (id_lugar, edificio, aula) VALUES ('24', '2','8');
INSERT INTO lugares (id_lugar, edificio, aula) VALUES ('25', '2','9');
INSERT INTO lugares (id_lugar, edificio, aula) VALUES ('26', '2','10');
INSERT INTO lugares (id_lugar, edificio, aula) VALUES ('27', '2','11');
INSERT INTO lugares (id_lugar, edificio, aula) VALUES ('28', '2','12');
INSERT INTO lugares (id_lugar, edificio, aula) VALUES ('29', '2','13');
INSERT INTO lugares (id_lugar, edificio, aula) VALUES ('30', '2','14');
INSERT INTO lugares (id_lugar, edificio, aula) VALUES ('31', '2','15');
INSERT INTO lugares (id_lugar, edificio, aula) VALUES ('32', '2','16');
--Agregar apartados
INSERT INTO apartados (id_apartado, matricula, id_equipo, id_lugar, grupo, fecha, hora_inicio, hora_final) VALUES ('1', 'S17004061', '1', '31', '601-ISW', '03-04-20', '7:00', '9:00');
INSERT INTO apartados (id_apartado, matricula, id_equipo, id_lugar, grupo, fecha, hora_inicio, hora_final) VALUES ('2', 'P58285658', '2', '32', '401-ISQ', '03-04-20', '10:00', '12:00');

Select * from apartados
