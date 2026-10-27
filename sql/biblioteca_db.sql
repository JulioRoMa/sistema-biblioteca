CREATE DATABASE IF NOT EXISTS biblioteca_db;
USE biblioteca_db;

CREATE TABLE IF NOT EXISTS usuarios (
    id_usuario INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    apellidos VARCHAR(150) NOT NULL,
    correo VARCHAR(150) NOT NULL,
    telefono VARCHAR(30) NOT NULL,
    direccion VARCHAR(255) NOT NULL,
    fecha_registro DATE NOT NULL,
    estado VARCHAR(50) NOT NULL
);
