CREATE DATABASE IF NOT EXISTS crowdfunding;
USE crowdfunding;

CREATE TABLE Pais (
    idPais INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL
);

CREATE TABLE Categoria (
    idCategoria INT PRIMARY KEY AUTO_INCREMENT,
    nombreCategoria VARCHAR(100) NOT NULL
);

CREATE TABLE Usuario (
    idUsuario INT PRIMARY KEY AUTO_INCREMENT,
    nombreUsuario VARCHAR(50) NOT NULL,
    contrasenia VARCHAR(100) NOT NULL,
    telefono VARCHAR(20) NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    mail VARCHAR(100) NOT NULL
);

CREATE TABLE Proyecto (
    idProyecto INT PRIMARY KEY AUTO_INCREMENT,
    nombreProyecto VARCHAR(100) NOT NULL,
    descripcion TEXT NOT NULL,
    montoMeta DECIMAL(10,2) NOT NULL,
    fechaIni DATE NOT NULL,
    fechaFin DATE NOT NULL,
    foto TEXT NOT NULL,
    estado VARCHAR(20) NOT NULL,
    montoRecaudado DECIMAL(10,2) NOT NULL,
    idPais INT NOT NULL,
    idCreador INT NOT NULL,
    idCategoria INT NOT NULL,
    FOREIGN KEY (idPais) REFERENCES Pais(idPais),
    FOREIGN KEY (idCreador) REFERENCES Usuario(idUsuario),
    FOREIGN KEY (idCategoria) REFERENCES Categoria(idCategoria)
);

CREATE TABLE Comentario (
    idComentario INT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(50),
    fecha DATE,
    descripcion TEXT,
    idProyecto INT,
    idUsuario INT,
    FOREIGN KEY (idProyecto) REFERENCES Proyecto(idProyecto),
    FOREIGN KEY (idUsuario) REFERENCES Usuario(idUsuario)
);

CREATE TABLE Donacion (
    idDonacion INT PRIMARY KEY AUTO_INCREMENT,
    monto DECIMAL(10,2),
    comentario TEXT,
    fecha DATE,
    idDonante INT,
    idProyecto INT,
    FOREIGN KEY (idDonante) REFERENCES Usuario(idUsuario),
    FOREIGN KEY (idProyecto) REFERENCES Proyecto(idProyecto)
);

CREATE TABLE Arrepentimiento_Donacion (
    idDonacion INT PRIMARY KEY,
    motivo TEXT,
    fechaCancelacion DATE,
    FOREIGN KEY (idDonacion) REFERENCES Donacion(idDonacion)
);

CREATE TABLE Cancelacion_Proyecto (
    idProyecto INT PRIMARY KEY,
    motivo TEXT,
    fecha DATE,
    FOREIGN KEY (idProyecto) REFERENCES Proyecto(idProyecto)
);

CREATE TABLE Avance_Proyecto (
    idProyecto INT,
    idAvance INT,
    descripcion TEXT,
    foto TEXT,
    fecha DATE,
    PRIMARY KEY (idProyecto, idAvance),
    FOREIGN KEY (idProyecto) REFERENCES Proyecto(idProyecto)
);