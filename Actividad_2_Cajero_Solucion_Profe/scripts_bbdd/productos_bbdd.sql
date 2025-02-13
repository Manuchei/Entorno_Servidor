create database productos_bbdd;
use productos_bbdd;
create table familias
(id_familia int not null auto_increment primary key,
nombre varchar(45) not null
);

create table productos
(id_producto dec(15) not null primary key,
descripcion varchar(100) not null,
precio double,
stock int,
fecha_alta date,
id_familia int not null,
foreign key(id_familia) references familias(id_familia)
);
insert into familias
values (1,'ROPA'), (2, 'BEBIDAS'), (3, 'FERRETERIA'), (4, 'COMPLEMENTOS');

insert into productos
values(1001,'Camisa verde hombre talla 46', 34,50,'2023-07-07',1);
insert into productos
values(1002,'Camisa verde mujer talla XS', 34,50,'2023-05-07',1);
insert into productos
values(1003,'Pantalon negro hombre talla 46', 34,50,'2022-07-07',1);
insert into productos
values(1004,'Pantalon rosa mujer talla M', 34,50,'2021-07-07',1);

create table usuarios
( username varchar(45) not null primary key,
 password varchar(45) not null,
 email varchar(100) not null unique,
 nombre varchar(45),
 apellidos varchar(100),
 created_at date,
 enabled int
);
insert into usuarios values
('tomas','tomasin','tomas@fp.es','Tomas', 'Escudero','2019-11-06',1),
('eva','evita','eva@fp.es','Eva', 'Goma','2020-11-06',1),
('sara','sarita','sara@fp.es','Sara', 'Varas','2021-11-06',1),
('ramon','ramoncin','ramon@fp.es','Ramon', 'Gutierrez','2019-12-06',1);




















