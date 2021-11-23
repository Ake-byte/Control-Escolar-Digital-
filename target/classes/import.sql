--USUARIO AUTORIZADO

insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled) values('Dr. Carlos Israel', 'Aguirre', 'Vélez',	'c.aguirre.velez@gmail.com', '$2a$10$xaaruwV8x92ds7aPZ93fou9EipWxEBVwloSRK7HwvXlEeCDkpYwt6', TRUE);
INSERT INTO roles (user_id, authority, authority_name) VALUES(1, 'ROLE_ADMIN', 'Personal Autorizado');

--ALUMNOS

insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled, clave_CURP, telefono_celular, genero, foto_status, acta_status, pasaporte_status, curp_status,calificaciones_licenciatura_status,diploma_licenciatura_status,cedula_licenciatura_status,acreditacion_ingles_status,calificaciones_maestria_status,acta_examen_maestria_status,diploma_maestria_status,cedula_maestria_status) values('Alan','Franco','Ake','al.fr.ake@gmail.com','$2a$10$bGLQnSbjwAW1uV1whnRZbeoejiFRUIBB0zZN.bo/X6DyqEzXGnyQi',TRUE,'FAAA991110HMCRKL09', '5520657474','Hombre', 0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO roles (user_id, authority, authority_name) VALUES(2, 'ROLE_USER2', 'Alumno');

insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled, genero, foto_status, acta_status, pasaporte_status, curp_status) values('Edgar Ramón', 'Hernández', 'Martínez',	'edgar70fm@gmail.com', '$2a$10$hhq0CuTPkDoxxbFRvXMQLuBxKTjiip98PhsO3E/Hx66T1vFM4TIWe',TRUE,'Hombre',0,0,0,0);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled, genero, foto_status, acta_status, pasaporte_status, curp_status) values('Guadalupe', 'Ramírez', 'Campos',	'lupita.folmt@gmail.com', '$2a$10$D/2qw9OOxfg2i/oeJwOkLOoglB9ohd8SbmhGyJtp0igNI3M4TgQAy',TRUE, 'Mujer',0,0,0,0);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled, genero, foto_status, acta_status, pasaporte_status, curp_status) values('Víctor Manuel', 'Melgarejo', 'Cázares',	'vmelgarejocaz@gmail.com', '$2a$10$jolsV/dR6BhNVVQ6FV/sPOWa4cHFfevLZQJN5N5WUgVVxtipyqS5S',TRUE,'Hombre',0,0,0,0);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled, genero, foto_status, acta_status, pasaporte_status, curp_status) values('Grisel', 'Hernández', 'Cortés',	'ghciqi@gmail.com', '$2a$10$/mUc4/VMQXENpO1t9yMHWe9/mCsHwhOygC1SqIPYL6QG9CKGYPeje',TRUE, 'Mujer',0,0,0,0);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled, genero, foto_status, acta_status, pasaporte_status, curp_status) values('Rayko', 'Amaro', 'Hernández', 'ramah920425@gmail.com', '$2a$10$eHXHUCQ8lhB4JJCeIEbvFeWWGT1aNh6etQLSq7U4dYBQnjqtSqJMy',TRUE,'Hombre',0,0,0,0,40);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled, genero, foto_status, acta_status, pasaporte_status, curp_status) values('Alfonso Israel', 'Guzmán', 'Montaño', 'alfonso.Guzman21@outlook.com', '$2a$10$E/GuCsjS20ZfZNg7JJ.dRuv4Vcp9DEJlNvdBMHWYZbyWvvt6hjw56',TRUE,'Hombre',0,0,0,0);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled, genero, foto_status, acta_status, pasaporte_status, curp_status) values('Josué', 'Lozada', 'Coronel', 'lozadajosue97@gmail.com', '$2a$10$ZhNLouyPvpAcZywRYrlO.O5jdQlHRHJ.jxDzJhvsefB/Bx4tW6rOi',TRUE,'Hombre',0,0,0,0);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled, genero, foto_status, acta_status, pasaporte_status, curp_status) values('Karla Scanda', 'Raymundo', 'Silva',	'karlascanda@gmail.com', '$2a$10$zioBoiO7xlXfv59wqEsGeO674ATAIg/EL1YsurZAL3OBpdvrDDw1i',TRUE, 'Mujer',0,0,0,0);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled, genero, foto_status, acta_status, pasaporte_status, curp_status) values('Camilo', 'Serrano', 'Fuentes',	'camilosf.92@gmail.com', '$2a$10$DKrGizoxOMGu84/NIpq02e6ktGtajQ7JdPfz6uL8jUxjHyIhxWyDm',TRUE,'Hombre',0,0,0,0,28);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled, genero, foto_status, acta_status, pasaporte_status, curp_status) values('Ricardo Alejandro', 'Terrero', 'Figueredo','ricardoterrero92@gmail.com', '$2a$10$lseVlUdPWiu1v1brnPIurubowz5zXh2jUf1pGJ/z24WOjXxFEvqxy',TRUE,'Hombre',0,0,0,0);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled, genero, foto_status, acta_status, pasaporte_status, curp_status) values('Yamil Eusebio', 'Divó', 'Matos','yamileusebio@gmail.com','$2a$10$c80VCGlpBvIF9RlM4y8qWOqGZTPnsl1ccCxSO5xs63Bb16NRK4etK',TRUE,'Hombre',0,0,0,0);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled, genero, foto_status, acta_status, pasaporte_status, curp_status) values('David Iván', 'Villalva', 'Mejorada','idavid121.dv@gmail.com', '$2a$10$MCGlelu5znUBO8sA8mLE0OcnIEcnO/c13KOdYf832qABhF0OlFKK6',TRUE,'Hombre',0,0,0,0);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled, genero, foto_status, acta_status, pasaporte_status, curp_status) values('Diana Catalina', 'Verduzco','Flores','diana.dcvf@gmail.com', '$2a$10$fEnf/onAVsrY.d9OjtLbJOU0cCZXMl2GiShyMWsb/Rr.dwaxIe64S',TRUE, 'Mujer',0,0,0,0);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled, genero, foto_status, acta_status, pasaporte_status, curp_status) values('Luis Alberto', 'Díaz', 'Paneque','ladpaneque@gmail.com', '$2a$10$w/Qo6YgO6e4cZP9oPq4FHeaGTIsSOY0lUnC3bvYqhkwtGWwiu/aMy',TRUE,'Hombre',0,0,0,0);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled, genero, foto_status, acta_status, pasaporte_status, curp_status) values('Luis Ángel', 'Tavera', 'Carrasco','ltaveraac@gmail.com', '$2a$10$WsQTgJXJYJ.wo9pVyRpUIu9kLSgw2uK91y1LjRpQpFMN7.7INzuS6',TRUE,'Hombre',0,0,0,0);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled, genero, foto_status, acta_status, pasaporte_status, curp_status) values('Pilar Amparo', 'Morgado', 'Aucar','pmorgadoaucar1995@gmail.com', '$2a$10$IMpwlhMWdft5A21nFkFns.bYK5ESvPj01MMWoCd8O.DWTHK6F9Pga',TRUE, 'Mujer',0,0,0,0);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled, genero, foto_status, acta_status, pasaporte_status, curp_status) values('Roxana', 'Paz', 'García','roxanapaz396@gmail.com', '$2a$10$Qbo6RSrxneCOhmF7O5lQpepV.ZMBQpVc0HWsEGpy6dL4cJn8n13UG',TRUE, 'Mujer',0,0,0,0);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled, genero, foto_status, acta_status, pasaporte_status, curp_status) values('Francisco Daniel', 'Ramírez', 'Apodaca','apodaca9207@gmail.com', '$2a$10$8/n4xz.4radWshhjSMh.huS.RQfbaf04I1exIn/LNOifFav/ijdLe',TRUE,'Hombre',0,0,0,0,30);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled, genero, foto_status, acta_status, pasaporte_status, curp_status) values('Omar', 'Reséndiz', 'Hernández','ommarrehe@gmail.com', '$2a$10$XQMSxigv.GMg/fieUVV.tubVH7NDZkyy.YQ2et0MUQX.wT16Qbtfu',TRUE,'Hombre',0,0,0,0);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled, genero, foto_status, acta_status, pasaporte_status, curp_status) values('Edgar Obed', 'Pérez', 'Reyes','edgarp656@gmail.com', '$2a$10$xsku9.4WGNC8LCkuZV0Br.7r/deP.Aw5P23.6N.etAqCmZvitP7EC',TRUE,'Hombre',0,0,0,0);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled, genero, foto_status, acta_status, pasaporte_status, curp_status) values('Erik Javier', 'del Angel', 'Gómez',	'erikdelangelgomez@gmail.com', '$2a$10$4wUurl37.dUAnJgzE2f13OpjxhXEYD7THFKA9LsInHreVTNUQ.otu',TRUE,'Hombre',0,0,0,0);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled, genero, foto_status, acta_status, pasaporte_status, curp_status) values('Hebert Rodrigo','Mojica','Molina','rodrigo1993mx@gmail.com','$2a$10$NKPFzDeEHT28XwjHQKmDOuur5u3DGDkM3TYIjp3Grml4kbkhLnXJO',TRUE,'Hombre',0,0,0,0);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled, genero, foto_status, acta_status, pasaporte_status, curp_status) values('Lucero','Torres','López','lucerolu7@gmail.com','$2a$10$8fp8pIZQ6lpMq0Ll0NNWsu6.p7/uyXXBy6ySi2k8VOMUDE3tgn8VW',TRUE, 'Mujer',0,0,0,0);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled, genero, foto_status, acta_status, pasaporte_status, curp_status) values('Heraclio','Heredia','Ureta','heracliohu@gmail.com','$2a$10$udLvwI2rhKNi2DFe/KUS9.HuhQGmH7EYlvjusi5GeRw1CexpHNlN6',TRUE,'Hombre',0,0,0,0);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled, genero, foto_status, acta_status, pasaporte_status, curp_status) values('Yeisy Clara','López','Conde','ylopezconde@gmail.com','$2a$10$jltWZZP5rl0HjGImFH/XJ.3y.r4EqyG5yAADtzGWZiZiHZ4RJIEIi',TRUE, 'Mujer',0,0,0,0);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled, genero, foto_status, acta_status, pasaporte_status, curp_status) values('Paula Montserrat','Crespo','Barrera','moncbarrera@gmail.com','$2a$10$ULyEGz8WzjRQkHbF86uwaeAtSX/XMRchqIDRCyGKpCcdAsgUJLzwu',TRUE, 'Mujer',0,0,0,0);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled, genero, foto_status, acta_status, pasaporte_status, curp_status) values('Yosuan','Ávila','García','yosuan.ag87@gmail.com','$2a$10$p0CFB5troI2tRle2cIjGSeEUi1EjOd4cye7bDPDcSEiVasnd9NA72',TRUE,'Hombre',0,0,0,0);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled, genero, foto_status, acta_status, pasaporte_status, curp_status) values('Juvencio','Vázquez','Samperio','juvencio_ipn@hotmail.com','$2a$10$LU1mTnaiMNINwZ4zDyvhYeQljVTFFLW5bsRBCR6u9b7TPrAEtclIO',TRUE,'Hombre',0,0,0,0);

INSERT INTO roles (user_id, authority, authority_name) VALUES(3, 'ROLE_USER2', 'Alumno');
INSERT INTO roles (user_id, authority, authority_name) VALUES(4, 'ROLE_USER2', 'Alumno');
INSERT INTO roles (user_id, authority, authority_name) VALUES(5, 'ROLE_USER2', 'Alumno');
INSERT INTO roles (user_id, authority, authority_name) VALUES(6, 'ROLE_USER2', 'Alumno');
INSERT INTO roles (user_id, authority, authority_name) VALUES(7, 'ROLE_USER2', 'Alumno');
INSERT INTO roles (user_id, authority, authority_name) VALUES(8, 'ROLE_USER2', 'Alumno');
INSERT INTO roles (user_id, authority, authority_name) VALUES(9, 'ROLE_USER2', 'Alumno');
INSERT INTO roles (user_id, authority, authority_name) VALUES(10, 'ROLE_USER2', 'Alumno');
INSERT INTO roles (user_id, authority, authority_name) VALUES(11, 'ROLE_USER2', 'Alumno');
INSERT INTO roles (user_id, authority, authority_name) VALUES(12, 'ROLE_USER2', 'Alumno');
INSERT INTO roles (user_id, authority, authority_name) VALUES(13, 'ROLE_USER2', 'Alumno');
INSERT INTO roles (user_id, authority, authority_name) VALUES(14, 'ROLE_USER2', 'Alumno');
INSERT INTO roles (user_id, authority, authority_name) VALUES(15, 'ROLE_USER2', 'Alumno');
INSERT INTO roles (user_id, authority, authority_name) VALUES(16, 'ROLE_USER2', 'Alumno');
INSERT INTO roles (user_id, authority, authority_name) VALUES(17, 'ROLE_USER2', 'Alumno');
INSERT INTO roles (user_id, authority, authority_name) VALUES(18, 'ROLE_USER2', 'Alumno');
INSERT INTO roles (user_id, authority, authority_name) VALUES(19, 'ROLE_USER2', 'Alumno');
INSERT INTO roles (user_id, authority, authority_name) VALUES(20, 'ROLE_USER2', 'Alumno');
INSERT INTO roles (user_id, authority, authority_name) VALUES(21, 'ROLE_USER2', 'Alumno');
INSERT INTO roles (user_id, authority, authority_name) VALUES(22, 'ROLE_USER2', 'Alumno');
INSERT INTO roles (user_id, authority, authority_name) VALUES(23, 'ROLE_USER2', 'Alumno');
INSERT INTO roles (user_id, authority, authority_name) VALUES(24, 'ROLE_USER2', 'Alumno');
INSERT INTO roles (user_id, authority, authority_name) VALUES(25, 'ROLE_USER2', 'Alumno');
INSERT INTO roles (user_id, authority, authority_name) VALUES(26, 'ROLE_USER2', 'Alumno');
INSERT INTO roles (user_id, authority, authority_name) VALUES(27, 'ROLE_USER2', 'Alumno');
INSERT INTO roles (user_id, authority, authority_name) VALUES(28, 'ROLE_USER2', 'Alumno');
INSERT INTO roles (user_id, authority, authority_name) VALUES(29, 'ROLE_USER2', 'Alumno');
INSERT INTO roles (user_id, authority, authority_name) VALUES(30, 'ROLE_USER2', 'Alumno');