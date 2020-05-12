INSERT INTO region (id, nombre) VALUES (1, 'Sudamérica');
INSERT INTO region (id, nombre) VALUES (2, 'Centroamérica');
INSERT INTO region (id, nombre) VALUES (3, 'Norteamérica');
INSERT INTO region (id, nombre) VALUES (4, 'Europa');
INSERT INTO region (id, nombre) VALUES (5, 'Asia');
INSERT INTO region (id, nombre) VALUES (6, 'Africa');
INSERT INTO region (id, nombre) VALUES (7, 'Oceanía');
INSERT INTO region (id, nombre) VALUES (8, 'Antártida');

INSERT INTO cliente (region_id, nombre, apellido, email, create_at) VALUES(1, 'Andrés', 'Guzmán', 'profesor@bolsadeideas.com', '2018-01-01');
INSERT INTO cliente (region_id, nombre, apellido, email, create_at) VALUES(2, 'Mr. John', 'Doe', 'john.doe@gmail.com', '2018-01-02');
INSERT INTO cliente (region_id, nombre, apellido, email, create_at) VALUES(4, 'Linus', 'Torvalds', 'linus.torvalds@gmail.com', '2018-01-03');
INSERT INTO cliente (region_id, nombre, apellido, email, create_at) VALUES(4, 'Rasmus', 'Lerdorf', 'rasmus.lerdorf@gmail.com', '2018-01-04');
INSERT INTO cliente (region_id, nombre, apellido, email, create_at) VALUES(4, 'Erich', 'Gamma', 'erich.gamma@gmail.com', '2018-02-01');
INSERT INTO cliente (region_id, nombre, apellido, email, create_at) VALUES(3, 'Richard', 'Helm', 'richard.helm@gmail.com', '2018-02-10');
INSERT INTO cliente (region_id, nombre, apellido, email, create_at) VALUES(3, 'Ralph', 'Johnson', 'ralph.johnson@gmail.com', '2018-02-18');
INSERT INTO cliente (region_id, nombre, apellido, email, create_at) VALUES(3, 'John', 'Vlissides', 'john.vlissides@gmail.com', '2018-02-28');
INSERT INTO cliente (region_id, nombre, apellido, email, create_at) VALUES(3, 'Dr. James', 'Gosling', 'james.gosling@gmail.com', '2018-03-03');
INSERT INTO cliente (region_id, nombre, apellido, email, create_at) VALUES(5, 'Magma', 'Lee', 'magma.lee@gmail.com', '2018-03-04');
INSERT INTO cliente (region_id, nombre, apellido, email, create_at) VALUES(6, 'Tornado', 'Roe', 'tornado.roe@gmail.com', '2018-03-05');
INSERT INTO cliente (region_id, nombre, apellido, email, create_at) VALUES(7, 'Jade', 'Doe', 'jane.doe@gmail.com', '2018-03-06');

INSERT INTO `usuario` (username, password, state, nombre, apellido, email) VALUES ('fernando','$2a$10$PpDvBfU.wWJmPdWqeNJsgeYdHHN.G3RYL5PBcgM8lGyJ35jDTyNaC',1, 'Fernando', 'Ticona','hdxtremo@gmail.com');
INSERT INTO `usuario` (username, password, state, nombre, apellido, email) VALUES ('admin','$2a$10$kb./vvOWTSl5F1q4e3FAN.uMPZoC6ssXnVw1fGwpUrZohkrBMO06u',1, 'John', 'Doe','jhon.doe@bolsadeideas.com');

INSERT INTO `perfil` (nombre) VALUES ('ROLE_USER');
INSERT INTO `perfil` (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO `usuario_perfil` (usuario_id, perfil_id) VALUES (1, 1);
INSERT INTO `usuario_perfil` (usuario_id, perfil_id) VALUES (2, 2);
INSERT INTO `usuario_perfil` (usuario_id, perfil_id) VALUES (2, 1);
