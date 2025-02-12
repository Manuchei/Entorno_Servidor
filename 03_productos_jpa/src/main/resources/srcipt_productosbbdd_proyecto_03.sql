create database productosbbdd;
use productosbbdd;
create table productos
(id_producto int not null primary key,
descripcion varchar(200) not null,
precio double,
stock int,
fecha_alta date);
drop table productos;
insert into productos
values(1001, 'Camisa manga larga de hombre talla 5', 34, 45, '2022-07-07'),
(1002, 'Camisa manga corta de mujer talla XS', 314, 145, '2023-07-07'),
(1003, 'Pantalon negro de hombre talla 46', 80, 50, '2022-09-07'),
(1004, 'Pantalon blanco de mujer talla 40', 200, 45, '2024-03-07');
select * from productosbbdd.productos;