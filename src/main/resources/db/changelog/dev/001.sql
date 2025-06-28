--
-- PostgreSQL database dump
--

-- Dumped from database version 15.13
-- Dumped by pg_dump version 16.9 (Ubuntu 16.9-0ubuntu0.24.04.1)

-- Started on 2025-06-28 16:24:51 -03

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;



--
-- TOC entry 214 (class 1259 OID 16417)
-- Name: con_connections_seq; Type: SEQUENCE; Schema: public; Owner: conexify
--

CREATE SEQUENCE public.con_connections_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.con_connections_seq OWNER TO conexify;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 215 (class 1259 OID 16441)
-- Name: con_connections; Type: TABLE; Schema: public; Owner: conexify
--

CREATE TABLE public.con_connections (
    id bigint DEFAULT nextval('public.con_connections_seq'::regclass) NOT NULL,
    name character varying(255) NOT NULL,
    description text,
    method character varying(10) NOT NULL,
    auth_type character varying(10) NOT NULL,
    headers text,
    url text NOT NULL,
    path_variables text,
    query_params text,
    body text,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    connect_timeout_ms integer NOT NULL,
    read_timeout_ms integer NOT NULL,
    auth_token text,
    depends_on bigint,
    CONSTRAINT auth_type_check CHECK (((auth_type)::text = ANY (ARRAY['NO_AUTH'::text, 'BASIC_AUTH'::text, 'BEARER_TOKEN'::text, 'API_KEY'::text, 'OAUTH2'::text]))),
    CONSTRAINT con_connections_method_check CHECK (((method)::text = ANY (ARRAY['GET'::text, 'POST'::text, 'PUT'::text, 'DELETE'::text, 'PATCH'::text, 'HEAD'::text, 'OPTIONS'::text, 'TRACE'::text])))
);


ALTER TABLE public.con_connections OWNER TO conexify;

--
-- TOC entry 3408 (class 0 OID 16441)
-- Dependencies: 215
-- Data for Name: con_connections; Type: TABLE DATA; Schema: public; Owner: conexify
--

INSERT INTO public.con_connections (id, name, description, method, auth_type, headers, url, path_variables, query_params, body, created_at, updated_at, connect_timeout_ms, read_timeout_ms, auth_token, depends_on) VALUES (26, 'Get House Types', 'Obtener listado de subtipos de vivienda con su tipo asociado.', 'GET', 'OAUTH2', '{"Content-Type":"application/json"}', 'http://sistema.lea.com.ar/LEAMobile.WebService/CalculadorService.svc/houseTypes', NULL, NULL, NULL, '2025-06-27 15:42:28.159291', '2025-06-27 15:42:28.159291', 2000, 10000, NULL, 22);
INSERT INTO public.con_connections (id, name, description, method, auth_type, headers, url, path_variables, query_params, body, created_at, updated_at, connect_timeout_ms, read_timeout_ms, auth_token, depends_on) VALUES (14, 'Listing all resources', 'Listing all resources', 'GET', 'NO_AUTH', NULL, 'https://jsonplaceholder.typicode.com/posts', NULL, NULL, NULL, '2025-06-26 20:21:30.396705', '2025-06-26 20:21:30.396705', 2000, 10000, NULL, NULL);
INSERT INTO public.con_connections (id, name, description, method, auth_type, headers, url, path_variables, query_params, body, created_at, updated_at, connect_timeout_ms, read_timeout_ms, auth_token, depends_on) VALUES (15, 'Getting a resource', 'Getting a resource', 'GET', 'NO_AUTH', NULL, 'https://jsonplaceholder.typicode.com/posts/{id}', '{"id":"1"}', NULL, NULL, '2025-06-26 20:28:28.475529', '2025-06-26 20:28:28.475529', 2000, 10000, NULL, NULL);
INSERT INTO public.con_connections (id, name, description, method, auth_type, headers, url, path_variables, query_params, body, created_at, updated_at, connect_timeout_ms, read_timeout_ms, auth_token, depends_on) VALUES (16, 'Creating a resource', 'Creating a resource', 'POST', 'NO_AUTH', '{"Content-type":"application/json; charset=UTF-8"}', 'https://jsonplaceholder.typicode.com/posts', NULL, NULL, '{"title": "foo", "body": "bar", "userId": 1}', '2025-06-26 20:35:23.229019', '2025-06-26 20:35:23.229019', 2000, 10000, NULL, NULL);
INSERT INTO public.con_connections (id, name, description, method, auth_type, headers, url, path_variables, query_params, body, created_at, updated_at, connect_timeout_ms, read_timeout_ms, auth_token, depends_on) VALUES (17, 'Updating a resource', 'Updating a resource', 'PUT', 'NO_AUTH', '{"Content-type":"application/json; charset=UTF-8"}', 'https://jsonplaceholder.typicode.com/posts/{id}', '{"id":"1"}', NULL, '{"id": 1,"title": "foo", "body": "bar", "userId": 1 }', '2025-06-26 20:40:26.83045', '2025-06-26 20:40:26.83045', 2000, 10000, NULL, NULL);
INSERT INTO public.con_connections (id, name, description, method, auth_type, headers, url, path_variables, query_params, body, created_at, updated_at, connect_timeout_ms, read_timeout_ms, auth_token, depends_on) VALUES (18, 'Patching a resource', 'Patching a resource', 'PATCH', 'NO_AUTH', '{"Content-type":"application/json; charset=UTF-8"}', 'https://jsonplaceholder.typicode.com/posts/{id}', '{"id":"1"}', NULL, '{"title": "foo" }', '2025-06-26 20:41:55.472585', '2025-06-26 20:41:55.472585', 2000, 10000, NULL, NULL);
INSERT INTO public.con_connections (id, name, description, method, auth_type, headers, url, path_variables, query_params, body, created_at, updated_at, connect_timeout_ms, read_timeout_ms, auth_token, depends_on) VALUES (19, 'Deleting a resource', 'Deleting a resource', 'DELETE', 'NO_AUTH', NULL, 'https://jsonplaceholder.typicode.com/posts/{id}', '{"id":"1"}', NULL, NULL, '2025-06-26 20:43:03.080459', '2025-06-26 20:43:03.080459', 2000, 10000, NULL, NULL);
INSERT INTO public.con_connections (id, name, description, method, auth_type, headers, url, path_variables, query_params, body, created_at, updated_at, connect_timeout_ms, read_timeout_ms, auth_token, depends_on) VALUES (20, 'Filtering resources', 'Basic filtering is supported through query parameters.', 'GET', 'NO_AUTH', NULL, 'https://jsonplaceholder.typicode.com/posts', NULL, '{"userId":"1"}', NULL, '2025-06-26 20:44:48.742377', '2025-06-26 20:44:48.742377', 2000, 10000, NULL, NULL);
INSERT INTO public.con_connections (id, name, description, method, auth_type, headers, url, path_variables, query_params, body, created_at, updated_at, connect_timeout_ms, read_timeout_ms, auth_token, depends_on) VALUES (21, 'Listing nested resources', 'One level of nested route is available.', 'GET', 'NO_AUTH', NULL, 'https://jsonplaceholder.typicode.com/posts/{id}/comments', '{"id":"1"}', NULL, NULL, '2025-06-26 20:47:20.042476', '2025-06-26 20:47:20.042476', 2000, 10000, NULL, NULL);
INSERT INTO public.con_connections (id, name, description, method, auth_type, headers, url, path_variables, query_params, body, created_at, updated_at, connect_timeout_ms, read_timeout_ms, auth_token, depends_on) VALUES (22, 'Obtener JWT Token', 'Obtener JWT Token enviando credenciales de login validas en E-LEA.', 'POST', 'NO_AUTH', '{"Content-type":"application/json; charset=UTF-8"}', 'http://sistema.lea.com.ar/LEAMobile.WebService/CalculadorService.svc/token', NULL, NULL, '{"Name":"nkobilsky","Password":"verde"}', '2025-06-27 13:12:48.658043', '2025-06-27 13:12:48.658043', 2000, 10000, NULL, NULL);
INSERT INTO public.con_connections (id, name, description, method, auth_type, headers, url, path_variables, query_params, body, created_at, updated_at, connect_timeout_ms, read_timeout_ms, auth_token, depends_on) VALUES (28, 'Get Zones', 'Obtener listado completo de zonas.', 'GET', 'OAUTH2', '{"Content-Type":"application/json"}', 'http://sistema.lea.com.ar/LEAMobile.WebService/CalculadorService.svc/zones', NULL, NULL, NULL, '2025-06-28 18:41:44.714429', '2025-06-28 18:41:44.714429', 2000, 10000, NULL, 22);
INSERT INTO public.con_connections (id, name, description, method, auth_type, headers, url, path_variables, query_params, body, created_at, updated_at, connect_timeout_ms, read_timeout_ms, auth_token, depends_on) VALUES (30, 'Get Zone', 'Obtener zona correspondiente a un código postal.', 'GET', 'OAUTH2', '{"Content-Type":"application/json"}', 'http://sistema.lea.com.ar/LEAMobile.WebService/CalculadorService.svc/zone/{cp}', '{"cp":"1642"}', NULL, NULL, '2025-06-28 18:45:37.955413', '2025-06-28 18:45:37.955413', 2000, 10000, NULL, 22);
INSERT INTO public.con_connections (id, name, description, method, auth_type, headers, url, path_variables, query_params, body, created_at, updated_at, connect_timeout_ms, read_timeout_ms, auth_token, depends_on) VALUES (31, 'Get Currencies', 'Obtener monedas.', 'GET', 'OAUTH2', '{"Content-Type":"application/json"}', 'http://sistema.lea.com.ar/LEAMobile.WebService/CalculadorService.svc/currencies', NULL, NULL, NULL, '2025-06-28 18:47:41.530363', '2025-06-28 18:47:41.530363', 2000, 10000, NULL, 22);
INSERT INTO public.con_connections (id, name, description, method, auth_type, headers, url, path_variables, query_params, body, created_at, updated_at, connect_timeout_ms, read_timeout_ms, auth_token, depends_on) VALUES (32, 'Get House Valuation', 'Obtener valuación de vivienda ingresando: Código postal, Metros cuadrados, Tipo, Subtipo, Moneda (optativo, por default ARS).', 'GET', 'OAUTH2', '{"Content-Type":"application/json"}', 'http://sistema.lea.com.ar/LEAMobile.WebService/CalculadorService.svc/calculate/{cp}/{mts}/{houseTypeGroup}/{houseType}', '{"cp":"1429","mts":"100","houseTypeGroup":"2","houseType":"2"}', NULL, NULL, '2025-06-28 18:52:40.837667', '2025-06-28 18:52:40.837667', 2000, 10000, NULL, 22);


--
-- TOC entry 3415 (class 0 OID 0)
-- Dependencies: 214
-- Name: con_connections_seq; Type: SEQUENCE SET; Schema: public; Owner: conexify
--

SELECT pg_catalog.setval('public.con_connections_seq', 33, true);


--
-- TOC entry 3264 (class 2606 OID 16450)
-- Name: con_connections con_connections_pkey; Type: CONSTRAINT; Schema: public; Owner: conexify
--

ALTER TABLE ONLY public.con_connections
    ADD CONSTRAINT con_connections_pkey PRIMARY KEY (id);


-- Completed on 2025-06-28 16:24:51 -03

--
-- PostgreSQL database dump complete
--

