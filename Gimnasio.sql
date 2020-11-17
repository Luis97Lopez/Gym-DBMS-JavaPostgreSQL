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

CREATE TABLE gimnasio.Empleado(

	IdEmpleado BIGSERIAL NOT NULL,
	IdHorario BIGINT NOT NULL,
	Nombre VARCHAR(50) NOT NULL,
	Celular VARCHAR(15) NOT NULL,
	Sueldo DECIMAL NOT NULL,
	Dias VARCHAR(7) NOT NULL,

	CONSTRAINT PK_Empleado PRIMARY KEY (IdEmpleado),
	CONSTRAINT FK_Horario1 FOREIGN KEY (IdHorario) REFERENCES gimnasio.Horario(IdHorario)

);

CREATE TABLE gimnasio.Cliente(

	IdCliente BIGSERIAL NOT NULL,
	IdEmpleado BIGINT,
	Nombre VARCHAR(50) NOT NULL,
	Direccion VARCHAR(70) NOT NULL,

	CONSTRAINT PK_Cliente PRIMARY KEY (IdCliente),
	CONSTRAINT FK_Empleado3 FOREIGN KEY (IdEmpleado)REFERENCES gimnasio.Empleado(IdEmpleado),

);

CREATE TABLE gimnasio.Suscripcion(

	IdSuscripcion BIGSERIAL NOT NULL,
	IdEmpleado BIGINT NOT NULL,
	IdCliente BIGINT NOT NULL,
	Precio DECIMAL NOT NULL,
	Duracion VARCHAR(10) NOT NULL,
	Tipo VARCHAR(20) NOT NULL,
	Fecha DATE NOT NULL,
	Estado VARCHAR(20) NOT NULL,

	CONSTRAINT PK_Suscripcion PRIMARY KEY (IdSuscripcion),
	CONSTRAINT FK_Empleado4 FOREIGN KEY (IdEmpleado) REFERENCES gimnasio.Empleado(IdEmpleado),
	CONSTRAINT FK_Cliente1 FOREIGN KEY (IdCliente) REFERENCES gimnasio.Cliente(IdCliente)

);

CREATE TABLE gimnasio.Clase(

	IdClase BIGSERIAL NOT NULL,
	IdEmpleado BIGINT NOT NULL,
	IdHorario BIGINT NOT NULL,
	Nombre VARCHAR(50) NOT NULL,
	Cupo INT NOT NULL,

	CONSTRAINT PK_Clase PRIMARY KEY (IdClase),
	CONSTRAINT FK_Empleado5 FOREIGN KEY (IdEmpleado) REFERENCES gimnasio.Empleado(IdEmpleado),
	CONSTRAINT FK_Horario2 FOREIGN KEY (IdHorario) REFERENCES gimnasio.Horario(IdHorario)

);

CREATE TABLE gimnasio.Inscripcion(

	IdInscripcion BIGSERIAL NOT NULL,
	IdClase BIGINT NOT NULL,
	IdCliente BIGINT NOT NULL,

	CONSTRAINT PK_Inscripcion PRIMARY KEY (IdInscripcion),
	CONSTRAINT FK_Clase FOREIGN KEY (IdClase) REFERENCES gimnasio.Clase(IdClase),
	CONSTRAINT FK_Cliente3 FOREIGN KEY (IdCliente) REFERENCES gimnasio.Cliente(IdCliente)

);

CREATE TABLE gimnasio.Pago(

	IdPago BIGSERIAL NOT NULL,
	IdSuscripcion BIGINT NOT NULL,
	IdCliente BIGINT NOT NULL,
	Total DECIMAL NOT NULL,
	Fecha DATE NOT NULL,

	CONSTRAINT PK_Pago PRIMARY KEY (IdPago),
	CONSTRAINT FK_Suscripcion FOREIGN KEY (IdSuscripcion) REFERENCES gimnasio.Suscripcion(IdSuscripcion),
	CONSTRAINT FK_Cliente2 FOREIGN KEY (IdCliente) REFERENCES gimnasio.Cliente(IdCliente)

);

CREATE TABLE gimnasio.Articulo(
	IdArticulo BIGSERIAL NOT NULL,
	Nombre VARCHAR(40) NOT NULL,
	Precio DECIMAL NOT NULL,
	Existencia INT NOT NULL,
	
	CONSTRAINT PK_Articulo PRIMARY KEY (IdArticulo)
);

CREATE TABLE gimnasio.DetalleVenta(

	IdDetalleVenta BIGSERIAL NOT NULL,
	IdArticulo BIGINT NOT NULL,
	Cantidad INT NOT NULL,
	Total DECIMAL,

	CONSTRAINT PK_DetalleVenta PRIMARY KEY (IdDetalleVenta),
	CONSTRAINT FK_Articulo1 FOREIGN KEY (IdArticulo) REFERENCES gimnasio.Articulo(IdArticulo)

);

CREATE TABLE gimnasio.Venta(

	IdVenta BIGSERIAL NOT NULL,
	IdEmpleado BIGINT NOT NULL,
	IdDetalleVenta BIGINT NOT NULL,
	Fecha DATE NOT NULL,

	CONSTRAINT PK_Venta PRIMARY KEY (IdVenta),
	CONSTRAINT FK_Empleado1 FOREIGN KEY (IdEmpleado) REFERENCES gimnasio.Empleado(IdEmpleado),
	CONSTRAINT FK_DetalleVenta FOREIGN KEY (IdDetalleVenta) REFERENCES gimnasio.DetalleVenta(IdDetalleVenta)

);

CREATE TABLE gimnasio.DetalleCompra(

	IdDetalleCompra BIGSERIAL NOT NULL,
	IdArticulo BIGINT NOT NULL,
	Cantidad INT NOT NULL,
	Total DECIMAL,

	CONSTRAINT PK_DetalleCompra PRIMARY KEY (IdDetalleCompra),
	CONSTRAINT FK_Articulo2 FOREIGN KEY (IdArticulo) REFERENCES gimnasio.Articulo(IdArticulo)

);

CREATE TABLE gimnasio.Compra(

	IdCompra BIGSERIAL NOT NULL,
	IdEmpleado BIGINT NOT NULL,
	IdDetalleCompra BIGINT NOT NULL,
	Fecha DATE NOT NULL,

	CONSTRAINT PK_Compra PRIMARY KEY (IdCompra),
	CONSTRAINT FK_Empleado2 FOREIGN KEY (IdEmpleado) REFERENCES gimnasio.Empleado(IdEmpleado),
	CONSTRAINT FK_DetalleCompra FOREIGN KEY (IdDetalleCompra) REFERENCES gimnasio.DetalleCompra(IdDetalleCompra)

);

alter table gimnasio.Empleado
add constraint CK_SueldoEmpleado
check (Sueldo = 1500 or Sueldo = 2000 or Sueldo = 3000);
 
alter table gimnasio.Suscripcion
add constraint CK_TipoSuscripcion
check (Tipo = 'Semanal' or Tipo = 'Mensual' or Tipo = 'Semestral' or Tipo = 'Anual');

CREATE UNIQUE INDEX UK_NombreClase ON gimnasio.Clase (Nombre);

INSERT INTO gimnasio.Articulo (Nombre, Precio, Existencia)
VALUES ('Pesas', 1000.1, '1');

