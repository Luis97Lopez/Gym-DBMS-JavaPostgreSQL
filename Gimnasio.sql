-- Database: Gimnasio

-- DROP DATABASE "Gimnasio";

CREATE DATABASE "Gimnasio"
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Spanish_Mexico.1252'
    LC_CTYPE = 'Spanish_Mexico.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;
	
CREATE SCHEMA gimnasio;

CREATE TABLE gimnasio.Horario(
	IdHorario BIGSERIAL NOT NULL,
	HoraInicio TIME NOT NULL,
	HoraFin TIME NOT NULL,
	
	CONSTRAINT PK_Horario PRIMARY KEY (IdHorario)
);

CREATE TABLE gimnasio.Articulo(
	IdArticulo BIGSERIAL NOT NULL,
	Nombre VARCHAR(40) NOT NULL,
	Precio NUMERIC(2) NOT NULL,
	Existencia BOOLEAN NOT NULL,
	
	CONSTRAINT PK_Articulo PRIMARY KEY (IdArticulo)
);