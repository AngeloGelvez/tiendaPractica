DROP TABLE IF EXISTS carrito;
DROP TABLE IF EXISTS producto;
DROP TABLE IF EXISTS venta;
DROP TABLE IF EXISTS categoria;


CREATE TABLE categoria(
	id_categoria SERIAL CONSTRAINT pk_categoria PRIMARY KEY,
	nombre VARCHAR NOT NULL,
	descripcion VARCHAR NOT NULL
);

CREATE TABLE producto(
	id_producto SERIAL CONSTRAINT pk_producto PRIMARY KEY,
	nombre VARCHAR NOT NULL,
	descripcion VARCHAR NOT NULL,
	precio NUMERIC(6,2) NOT NULL,
	stock INT NOT NULL,
	imagen VARCHAR NOT NULL,
	id_categoria SERIAL NOT NULL,
	CONSTRAINT fk_id_categoria FOREIGN KEY(id_categoria) REFERENCES categoria(id_categoria)
);

CREATE TABLE venta(
	id_venta SERIAL CONSTRAINT pk_venta PRIMARY KEY,
	num_comprobante INT NOT NULL,
	fecha DATE NOT NULL,
	impuesto NUMERIC(5,2),
	total NUMERIC(8,2) NOT NULL
);

CREATE TABLE carrito(
	id_carrito SERIAL CONSTRAINT pk_carrito PRIMARY KEY,
	cantidad INT NOT NULL,
	precio NUMERIC(5,2) NOT NULL,
	id_producto SERIAL NOT NULL,
	CONSTRAINT fk_id_producto FOREIGN KEY(id_producto) REFERENCES producto(id_producto),
	id_venta SERIAL NOT NULL,
	CONSTRAINT fk_id_venta FOREIGN KEY(id_venta) REFERENCES venta(id_venta)
);

