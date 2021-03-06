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
	CONSTRAINT FK_Empleado3 FOREIGN KEY (IdEmpleado)REFERENCES gimnasio.Empleado(IdEmpleado)

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
	IdVenta BIGINT NOT NULL,
	IdArticulo BIGINT NOT NULL,
	Cantidad INT NOT NULL,
	Subtotal DECIMAL,

	CONSTRAINT FK_Articulo1 FOREIGN KEY (IdArticulo) REFERENCES gimnasio.Articulo(IdArticulo),
	CONSTRAINT FK_Venta FOREIGN KEY (IdVenta) REFERENCES gimnasio.Venta(IdVenta)
);

CREATE TABLE gimnasio.Venta(

	IdVenta BIGSERIAL NOT NULL,
	IdEmpleado BIGINT NOT NULL,
	Fecha DATE NOT NULL,
	Total DECIMAL,

	CONSTRAINT PK_Venta PRIMARY KEY (IdVenta),
	CONSTRAINT FK_Empleado1 FOREIGN KEY (IdEmpleado) REFERENCES gimnasio.Empleado(IdEmpleado)
	
);


CREATE TABLE gimnasio.DetalleCompra(
	IdCompra BIGINT NOT NULL,
	IdArticulo BIGINT NOT NULL,
	Cantidad INT NOT NULL,
	Subtotal DECIMAL,

	CONSTRAINT FK_Articulo2 FOREIGN KEY (IdArticulo) REFERENCES gimnasio.Articulo(IdArticulo),
	CONSTRAINT FK_Compra FOREIGN KEY (IdCompra) REFERENCES gimnasio.Compra(IdCompra)
);


CREATE TABLE gimnasio.Compra(

	IdCompra BIGSERIAL NOT NULL,
	IdEmpleado BIGINT NOT NULL,
	Fecha DATE NOT NULL,
	Total DECIMAL,

	CONSTRAINT PK_Compra PRIMARY KEY (IdCompra),
	CONSTRAINT FK_Empleado2 FOREIGN KEY (IdEmpleado) REFERENCES gimnasio.Empleado(IdEmpleado)
	
);

alter table gimnasio.Empleado
add constraint CK_SueldoEmpleado
check (Sueldo = 1500 or Sueldo = 2000 or Sueldo = 3000);
 
alter table gimnasio.Suscripcion
add constraint CK_TipoSuscripcion
check (Tipo = 'Semanal' or Tipo = 'Mensual' or Tipo = 'Semestral' or Tipo = 'Anual');

CREATE UNIQUE INDEX UK_CelularEmpleado ON gimnasio.Empleado (Celular);


CREATE OR REPLACE FUNCTION trigger_reduceCupo() 
RETURNS TRIGGER 
AS $$
	BEGIN
	   UPDATE gimnasio.Clase
		SET Cupo = Cupo-1
		WHERE IdClase = NEW.IdClase;
		return new;
	END;
$$
LANGUAGE plpgsql;

CREATE TRIGGER reduce_Cupo
AFTER INSERT ON gimnasio.Inscripcion
FOR EACH ROW
EXECUTE PROCEDURE trigger_reduceCupo();

CREATE OR REPLACE FUNCTION trigger_aumentaCupo() 
RETURNS TRIGGER 
AS $$
	BEGIN
	   UPDATE gimnasio.Clase
		SET Cupo = Cupo+1
		WHERE IdClase = OLD.IdClase;
		return new;
	END;
$$
LANGUAGE plpgsql;

CREATE TRIGGER aumenta_Cupo
AFTER delete ON gimnasio.Inscripcion
FOR EACH ROW
EXECUTE PROCEDURE trigger_aumentaCupo();

CREATE OR REPLACE FUNCTION trigger_calcular_total_compra() 
RETURNS TRIGGER 
AS $$
	DECLARE
		
	BEGIN
	   	NEW.Subtotal := NEW.cantidad * (SELECT Precio FROM gimnasio.Articulo WHERE IdArticulo=NEW.IdArticulo);
		
		UPDATE gimnasio.Compra
		SET Total = NEW.Subtotal * (1.16)
		WHERE IdCompra=NEW.IdCompra;
		RETURN NEW;
	END;
$$
LANGUAGE plpgsql;


CREATE TRIGGER calcula_total_compra
BEFORE INSERT or UPDATE
ON gimnasio.DetalleCompra
FOR EACH ROW
EXECUTE PROCEDURE trigger_calcular_total_compra();

CREATE OR REPLACE FUNCTION trigger_calcular_total_venta() 
RETURNS TRIGGER 
AS $$
	DECLARE
		
	BEGIN
	   	NEW.Subtotal := NEW.cantidad * (SELECT Precio FROM gimnasio.Articulo WHERE IdArticulo=NEW.IdArticulo);
		
		UPDATE gimnasio.Venta
		SET Total = NEW.Subtotal * (1.16)
		WHERE IdVenta=NEW.IdVenta;
		RETURN NEW;
	END;
$$
LANGUAGE plpgsql;

CREATE TRIGGER calcula_total_venta
BEFORE INSERT or UPDATE
ON gimnasio.DetalleVenta
FOR EACH ROW
EXECUTE PROCEDURE trigger_calcular_total_venta();



CREATE USER user_gerente WITH PASSWORD 'postgres';
CREATE USER user_empleado WITH PASSWORD 'postgres';
REVOKE ALL ON DATABASE "Gimnasio" FROM public;

CREATE ROLE gerente;
CREATE ROLE empleado;

GRANT CONNECT ON DATABASE "Gimnasio" TO gerente;
GRANT CONNECT ON DATABASE "Gimnasio" TO empleado;

GRANT USAGE ON SCHEMA gimnasio TO gerente;
GRANT USAGE ON SCHEMA gimnasio TO empleado;

GRANT gerente TO user_gerente;
GRANT empleado TO user_empleado;

REVOKE ALL ON ALL TABLES IN SCHEMA gimnasio FROM gerente;

GRANT SELECT ON TABLE
gimnasio.Empleado
TO gerente;

GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE 
gimnasio.Cliente, gimnasio.Suscripcion, gimnasio.Clase, gimnasio.Inscripcion, 
gimnasio.Horario, gimnasio.Pago, gimnasio.Venta, gimnasio.DetalleVenta, 
gimnasio.Compra, gimnasio.DetalleCompra, gimnasio.Articulo
TO gerente;
GRANT ALL ON ALL SEQUENCES IN SCHEMA gimnasio TO gerente;

GRANT SELECT ON TABLE
gimnasio.Empleado, gimnasio.Clase, gimnasio.Articulo, gimnasio.Horario
TO empleado;

GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE 
gimnasio.Cliente, gimnasio.Suscripcion, gimnasio.Inscripcion, gimnasio.Pago, 
gimnasio.Venta, gimnasio.DetalleVenta, gimnasio.Compra, gimnasio.DetalleCompra TO empleado;

GRANT ALL ON ALL SEQUENCES IN SCHEMA gimnasio TO empleado;
