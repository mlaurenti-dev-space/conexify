-- 1. Crear la secuencia para el ID
CREATE SEQUENCE public.con_connections_seq
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
  NO MAXVALUE
  CACHE 1;

-- 2. Crear la tabla con todos los campos mapeados

CREATE TABLE public.con_connections (
  id BIGINT PRIMARY KEY DEFAULT nextval('public.con_connections_seq'), 
  name VARCHAR(255) NOT NULL, 
  description TEXT, 
  method VARCHAR(10) NOT NULL CHECK ((method):: text = ANY (ARRAY[ 'GET', 'POST', 'PUT', 'DELETE', 'PATCH', 'HEAD', 'OPTIONS', 'TRACE' ] :: text[])), 
  auth_type VARCHAR(10) NOT NULL CHECK ((auth_type):: text = ANY (ARRAY[ 'NONE', 'BASIC', 'BEARER', 'API_KEY' ] :: text[])), 
  headers TEXT, 
  url TEXT NOT NULL, 
  path_variables TEXT, 
  query_params TEXT, 
  body TEXT, 
  created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL, 
  updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL
);


-- 3. (Opcional) Índices adicionales
CREATE INDEX idx_con_connections_name ON public.con_connections(name);
