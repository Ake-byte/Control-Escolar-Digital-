--USUARIO AUTORIZADO

INSERT INTO users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled) values('Dr. Carlos Israel', 'Aguirre', 'Vélez',	'c.aguirre.velez@gmail.com', '$2a$10$xaaruwV8x92ds7aPZ93fou9EipWxEBVwloSRK7HwvXlEeCDkpYwt6', TRUE);
INSERT INTO roles (user_id, authority, authority_name) VALUES(1, 'ROLE_ADMIN', 'Personal Autorizado');

--ALUMNOS

INSERT INTO users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled) values('Alan','Franco','Ake','al.fr.ake@gmail.com','$2a$10$bGLQnSbjwAW1uV1whnRZbeoejiFRUIBB0zZN.bo/X6DyqEzXGnyQi',TRUE);
INSERT INTO roles (user_id, authority, authority_name) VALUES(2, 'ROLE_USER2', 'Alumno');
INSERT INTO personal(user_id, fecha_nacimiento, clave_CURP, telefono_celular, genero, foto_status, acta_status, pasaporte_status, curp_status, pais_nacimiento,estado_nacimiento,lengua_indigena,discapacidad,enfermedad_permanente) VALUES(2,'1989-11-10','FAAA991110HMCRKL09', '5520657474','Hombre', 0,0,0,0, 'Spain','Ciudad de México',TRUE,TRUE,TRUE);
INSERT INTO academica(user_id, calificaciones_licenciatura_status,diploma_licenciatura_status,cedula_licenciatura_status,acreditacion_ingles_status,calificaciones_maestria_status,acta_examen_maestria_status,diploma_maestria_status,cedula_maestria_status) VALUES(2, 0,0,0,0,0,0,0,0);
INSERT INTO expediente(user_id, semestre, grado, estatus_escolar, beca_conacyt) VALUES(2,'A21', 'Maestría', 'Inscrito', TRUE);

INSERT INTO users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled) values('Edgar Ramón', 'Hernández', 'Martínez',	'edgar70fm@gmail.com', '$2a$10$hhq0CuTPkDoxxbFRvXMQLuBxKTjiip98PhsO3E/Hx66T1vFM4TIWe',TRUE);
INSERT INTO roles (user_id, authority, authority_name) VALUES(3, 'ROLE_USER2', 'Alumno');
INSERT INTO personal(user_id, fecha_nacimiento, clave_CURP, telefono_celular, genero, foto_status, acta_status, pasaporte_status, curp_status, pais_nacimiento,estado_nacimiento,lengua_indigena,discapacidad,enfermedad_permanente) VALUES(3,'1989-11-10','FAAA991110HMCRKL09', '5520657474','Hombre', 0,0,0,0, 'Mexico','Ciudad de México',TRUE,TRUE,TRUE);
INSERT INTO academica(user_id, calificaciones_licenciatura_status,diploma_licenciatura_status,cedula_licenciatura_status,acreditacion_ingles_status,calificaciones_maestria_status,acta_examen_maestria_status,diploma_maestria_status,cedula_maestria_status) VALUES(3, 0,0,0,0,0,0,0,0);
INSERT INTO expediente(user_id, semestre, grado, estatus_escolar, beca_conacyt) VALUES(3,'A21', 'Maestría', 'Inscrito', TRUE);

INSERT INTO users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled) values('Guadalupe', 'Ramírez', 'Campos',	'lupita.folmt@gmail.com', '$2a$10$D/2qw9OOxfg2i/oeJwOkLOoglB9ohd8SbmhGyJtp0igNI3M4TgQAy',TRUE);
INSERT INTO roles (user_id, authority, authority_name) VALUES(4, 'ROLE_USER2', 'Alumno');
INSERT INTO personal(user_id, fecha_nacimiento, clave_CURP, telefono_celular, genero, foto_status, acta_status, pasaporte_status, curp_status, pais_nacimiento,estado_nacimiento,lengua_indigena,discapacidad,enfermedad_permanente) VALUES(4,'1989-11-10','FAAA991110HMCRKL09', '5520657474','Mujer', 0,0,0,0, 'Mexico','Ciudad de México',FALSE,FALSE,FALSE);
INSERT INTO academica(user_id, calificaciones_licenciatura_status,diploma_licenciatura_status,cedula_licenciatura_status,acreditacion_ingles_status,calificaciones_maestria_status,acta_examen_maestria_status,diploma_maestria_status,cedula_maestria_status) VALUES(4, 0,0,0,0,0,0,0,0);
INSERT INTO expediente(user_id, semestre, grado, estatus_escolar, beca_conacyt) VALUES(4,'A21', 'Maestría', 'Egresado', TRUE);

INSERT INTO users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled) values('Víctor Manuel', 'Melgarejo', 'Cázares',	'vmelgarejocaz@gmail.com', '$2a$10$jolsV/dR6BhNVVQ6FV/sPOWa4cHFfevLZQJN5N5WUgVVxtipyqS5S',TRUE);
INSERT INTO roles (user_id, authority, authority_name) VALUES(5, 'ROLE_USER2', 'Alumno');
INSERT INTO personal(user_id, fecha_nacimiento, clave_CURP, telefono_celular, genero, foto_status, acta_status, pasaporte_status, curp_status, pais_nacimiento,estado_nacimiento,lengua_indigena,discapacidad,enfermedad_permanente) VALUES(5,'1989-11-10','FAAA991110HMCRKL09', '5520657474','Hombre', 0,0,0,0, 'Mexico','Ciudad de México',FALSE,FALSE,FALSE);
INSERT INTO academica(user_id, calificaciones_licenciatura_status,diploma_licenciatura_status,cedula_licenciatura_status,acreditacion_ingles_status,calificaciones_maestria_status,acta_examen_maestria_status,diploma_maestria_status,cedula_maestria_status) VALUES(5, 0,0,0,0,0,0,0,0);
INSERT INTO expediente(user_id, semestre, grado, estatus_escolar, beca_conacyt) VALUES(5,'A21', 'Maestría', 'Inscrito', TRUE);