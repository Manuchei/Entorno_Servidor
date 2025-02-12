create database productosbd_completo_V2;
use productosbd_completo_V2;
create table familias
( codigo int not null auto_increment primary key,
descripcion varchar(45) not null
);
create table productos
(codigo int not null auto_increment primary key,
descripcion varchar(45) not null,
precio_unitario decimal(11,2),
marca varchar(45),
color varchar(20),
codigo_familia int not null,
foreign key(codigo_familia) references familias(codigo)
);

-- FILAS DE FAMILIAS
INSERT INTO `familias` (`codigo`,`descripcion`) VALUES (1,'bebidas');
INSERT INTO `familias` (`codigo`,`descripcion`) VALUES (2,'ropa');
INSERT INTO `familias` (`codigo`,`descripcion`) VALUES (3,'zapatos');
INSERT INTO `familias` (`codigo`,`descripcion`) VALUES (4,'comida');
INSERT INTO `familias` (`codigo`,`descripcion`) VALUES (5,'ferreteria');
INSERT INTO `familias` (`codigo`,`descripcion`) VALUES (6,'deportes desde postman');
INSERT INTO `familias` (`codigo`,`descripcion`) VALUES (18,'modificada la familia desde PUT');
INSERT INTO `familias` (`codigo`,`descripcion`) VALUES (21,'teclados desde postman');
INSERT INTO `familias` (`codigo`,`descripcion`) VALUES (22,'ordenadores desde postman');
INSERT INTO `familias` (`codigo`,`descripcion`) VALUES (25,'espejos');
INSERT INTO `familias` (`codigo`,`descripcion`) VALUES (26,'portatiles');
-- FILAS DE PRODUCTOS
INSERT INTO `productos` (`codigo`,`descripcion`,`precio_unitario`,`codigo_familia`,`marca`,`color`) VALUES (2,'fanta',1.00,1,'fanta','naranja');
INSERT INTO `productos` (`codigo`,`descripcion`,`precio_unitario`,`codigo_familia`,`marca`,`color`) VALUES (3,'mirinda',0.50,1,'mirinda','limon');
INSERT INTO `productos` (`codigo`,`descripcion`,`precio_unitario`,`codigo_familia`,`marca`,`color`) VALUES (4,'pantalon',24.00,2,'levis','azul');
INSERT INTO `productos` (`codigo`,`descripcion`,`precio_unitario`,`codigo_familia`,`marca`,`color`) VALUES (5,'camisa',23.00,2,'dustin','azul');
INSERT INTO `productos` (`codigo`,`descripcion`,`precio_unitario`,`codigo_familia`,`marca`,`color`) VALUES (6,'chaleco',124.00,2,'dustin','negro');
INSERT INTO `productos` (`codigo`,`descripcion`,`precio_unitario`,`codigo_familia`,`marca`,`color`) VALUES (33,'falda',34.00,2,'nisu','rojo');
INSERT INTO `productos` (`codigo`,`descripcion`,`precio_unitario`,`codigo_familia`,`marca`,`color`) VALUES (34,'falda',34.00,2,'nisu','rojo');
INSERT INTO `productos` (`codigo`,`descripcion`,`precio_unitario`,`codigo_familia`,`marca`,`color`) VALUES (35,'falda',34.00,2,'nisu','rojo');
INSERT INTO `productos` (`codigo`,`descripcion`,`precio_unitario`,`codigo_familia`,`marca`,`color`) VALUES (36,'fanta',1.00,1,'fanta','naranja');
INSERT INTO `productos` (`codigo`,`descripcion`,`precio_unitario`,`codigo_familia`,`marca`,`color`) VALUES (37,'fanta',1.00,1,'fanta','naranja');

CREATE TABLE perfiles (
  ID_PERFIL int NOT NULL AUTO_INCREMENT,
  NOMBRE varchar(45) NOT NULL,
  PRIMARY KEY (`ID_PERFIL`)
);

INSERT INTO perfiles VALUES (1,'ROLE_ADMON'),(2,'ROLE_GESTOR'),(3,'ROLE_CLIENTE');
--
-- Table structure for table `usuarios`
--

CREATE TABLE `usuarios` (
  `USERNAME` varchar(45) NOT NULL,
  `PASSWORD` varchar(200) NOT NULL,
  `NOMBRE` varchar(50) DEFAULT NULL,
  `APELLIDOS` varchar(50) DEFAULT NULL,
  `DIRECCION` varchar(100) DEFAULT NULL,
  `ENABLED` int NOT NULL DEFAULT '1',
  `FECHA_REGISTRO` date DEFAULT NULL,
  PRIMARY KEY (`USERNAME`)
);

INSERT INTO `usuarios` VALUES 
	('amalia','{noop}amalia','amalia','sanchez','sevilla',1,'2021-06-01'),
	('cacaito@fp.com','cacaito','cacaito','lopez','madrid',1,'2021-06-01'),
	('eva@fp.com','{noop}evita','eva','perez','calle jazmin 20,\nsevilla',1,'2021-02-01'),
	('facundo','$2a$10$n9orzfuAMAcrFxkdDkf/6.b5ACMfsIgW7B1tTjYPnYhMJx6AfIXvO','Facundo','Estrella','Madrid',1,'2024-12-15'),
	('javier@fp.com','{noop}javierito','javier','perez','Madrid',1,'2021-06-01'),
	('ramon@fp.com','{noop}ramoncin','ramon','chu','Madrid',1,'2021-06-01'),
	('ricardo@fp.com','{noop}ricardito','ricardo','moreno','Cadiz',1,'2021-06-01'),
	('sara@fp.com','{noop}sarita','sara','martinez','calle rosal 10,\nmadrid',1,'2021-03-01'),
	('tomas@fp.com','{noop}tomasin','tomas','escudero','calle alamin 30,\nmadrid',1,'2021-01-01');
CREATE TABLE usuario_perfiles (
  USERNAME varchar(45) NOT NULL,
  ID_PERFIL int NOT NULL,
  PRIMARY KEY (USERNAME,ID_PERFIL),
  CONSTRAINT `usuario_perfiles_ibfk_1` FOREIGN KEY (USERNAME) REFERENCES usuarios (USERNAME),
  CONSTRAINT `usuario_perfiles_ibfk_2` FOREIGN KEY (ID_PERFIL) REFERENCES perfiles (ID_PERFIL)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO usuario_perfiles VALUES ('amalia',1),
										('cacaito@fp.com',1),
										('javier@fp.com',1),
										('ramon@fp.com',1),
										('ricardo@fp.com',1),
										('tomas@fp.com',1),
										('cacaito@fp.com',2),
										('eva@fp.com',2),
										('javier@fp.com',2),
										('ricardo@fp.com',2),
										('tomas@fp.com',2),
										('facundo',3),
										('sara@fp.com',3);


