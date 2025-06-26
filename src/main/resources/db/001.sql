-- 1. Crear tipos ENUM para los dos campos de enumeración
CREATE TYPE public.con_http_method_enum AS ENUM (
  'GET', 'POST', 'PUT', 'DELETE', 'PATCH', 'HEAD', 'OPTIONS', 'TRACE'
);

CREATE TYPE public.con_auth_type_enum AS ENUM (
  'NONE', 'BASIC', 'BEARER', 'API_KEY'
);

-- 2. Crear la secuencia para el ID
CREATE SEQUENCE public.con_connections_seq
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
  NO MAXVALUE
  CACHE 1;

-- 3. Crear la tabla con todos los campos mapeados
CREATE TABLE public.con_connections (
  id               BIGINT NOT NULL DEFAULT nextval('public.con_connections_seq'),
  name             VARCHAR(255)       NOT NULL,
  description      TEXT,
  method           public.con_http_method_enum NOT NULL,
  auth_type        public.con_auth_type_enum   NOT NULL,
  headers          TEXT,
  url              TEXT               NOT NULL,
  path_variables   TEXT,
  query_params     TEXT,
  body             TEXT,
  created_at       TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  updated_at       TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  CONSTRAINT con_connections_pkey PRIMARY KEY (id)
);

-- 4. (Opcional) Índices adicionales
CREATE INDEX idx_con_connections_name ON public.con_connections(name);
