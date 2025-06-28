# Conexify

Conexify es un microservicio reactivo construido con Spring Boot 3. Su objetivo
es almacenar definiciones de **conexiones HTTP** (método, URL, cabeceras,
autenticación y tiempos de espera) y exponer una API para gestionarlas y
ejecutarlas. El proyecto cuenta con soporte para PostgreSQL mediante R2DBC y se
distribuye con un entorno de desarrollo basado en Docker.

## Características principales

- **WebFlux + R2DBC**: toda la lógica de acceso a datos y las operaciones sobre
  la API se realizan de forma no bloqueante.
- **Estrategias de autenticación** (No Auth, Bearer/OAuth2, etc.) implementadas
  en `strategies` y `processors`.
- **MapStruct** para el mapeo entre entidades y DTOs.
- **Jasypt** para el cifrado transparente de campos sensibles como tokens.
- **Liquibase** para el versionado de la base de datos (ver `src/main/resources/db`).
- **OpenAPI/Swagger** a través de `springdoc-openapi`.

## Organización del código

- `controllers` – define los endpoints REST (`ConConnectionController`).
- `services` y `facades` – contienen la lógica de negocio y la capa intermedia
  entre controlador y repositorio.
- `repositories` – acceso a datos reactivo con Spring Data (`ConConnectionRepository`).
- `builders` y `factories` – crean instancias de `WebClient` y las estrategias de
  autenticación necesarias para cada conexión.
- `entities`, `dtos` y `mappers` – representación de los datos y conversiones entre
  la capa de persistencia y la capa de presentación.
- `utils`, `converters` y demás paquetes de soporte.

## Ejecución rápida

El proyecto incluye un archivo `docker-compose.yml` que levanta una base de datos
PostgreSQL y la aplicación en modo de desarrollo:

```bash
docker-compose up
```

También se puede ejecutar con Maven de forma tradicional:

```bash
./mvnw spring-boot:run
```

Una vez en marcha, la documentación de la API estará disponible en
`/swagger-ui.html`.

