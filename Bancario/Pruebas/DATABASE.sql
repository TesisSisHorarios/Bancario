--DATABASE
-- Database: sisbancariodb

-- DROP DATABASE sisbancariodb;

CREATE DATABASE sisbancariodb
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'es_EC.UTF-8'
    LC_CTYPE = 'es_EC.UTF-8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

-- Table: public.persona

-- DROP TABLE public.persona;

CREATE TABLE public.persona
(
    cpersona integer NOT NULL,
    direccion character varying(255) COLLATE pg_catalog."default",
    edad integer,
    genero character varying(1) COLLATE pg_catalog."default",
    identificaion character varying(10) COLLATE pg_catalog."default",
    nombre character varying(255) COLLATE pg_catalog."default",
    telefono character varying(10) COLLATE pg_catalog."default",
    CONSTRAINT persona_pkey PRIMARY KEY (cpersona),
    CONSTRAINT uk5q9pydpll9hd9s4g7vqfeim3b UNIQUE (identificaion)

)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.persona
    OWNER to root;


-- Table: public.cliente

-- DROP TABLE public.cliente;

CREATE TABLE public.cliente
(
    contrasena character varying(255) COLLATE pg_catalog."default",
    estado boolean,
    clienteid integer NOT NULL,
    CONSTRAINT cliente_pkey PRIMARY KEY (clienteid),
    CONSTRAINT fkltuad7gvir4i2nu5wse9mnb8 FOREIGN KEY (clienteid)
        REFERENCES public.persona (cpersona) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.cliente
    OWNER to root;


-- Table: public.cuentaid

-- DROP TABLE public.cuentaid;

CREATE TABLE public.cuentaid
(
    ccuenta bigint NOT NULL,
    CONSTRAINT cuentaid_pkey PRIMARY KEY (ccuenta)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.cuentaid
    OWNER to root;




-- Table: public.cuenta

-- DROP TABLE public.cuenta;

CREATE TABLE public.cuenta
(
    ccuenta bigint NOT NULL,
    fhasta timestamp without time zone NOT NULL,
    estado boolean NOT NULL,
    fdesde timestamp without time zone NOT NULL,
    saldoinicial numeric(19,2),
    tipocuenta character varying(255) COLLATE pg_catalog."default" NOT NULL,
    clienteid integer NOT NULL,
    CONSTRAINT cuenta_pkey PRIMARY KEY (ccuenta, fhasta),
    CONSTRAINT fk1x7mj3iih1qlvrj88x5u804ps FOREIGN KEY (ccuenta)
        REFERENCES public.cuentaid (ccuenta) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fktiah1fi6lm797bbc6bkex91ie FOREIGN KEY (clienteid)
        REFERENCES public.cliente (clienteid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.cuenta
    OWNER to root;




-- Table: public.movimiento

-- DROP TABLE public.movimiento;

CREATE TABLE public.movimiento
(
    cmovimiento integer NOT NULL,
    fecha timestamp without time zone,
    saldo numeric(19,2),
    tipomovimiento character varying(1) COLLATE pg_catalog."default",
    valor numeric(19,2),
    ccuenta bigint NOT NULL,
    CONSTRAINT movimiento_pkey PRIMARY KEY (cmovimiento),
    CONSTRAINT fkbn404f4hhs1236x91pbixn2ri FOREIGN KEY (ccuenta)
        REFERENCES public.cuentaid (ccuenta) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.movimiento
    OWNER to root;