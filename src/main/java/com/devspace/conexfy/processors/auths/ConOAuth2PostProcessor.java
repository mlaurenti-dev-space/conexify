package com.devspace.conexfy.processors.auths;

import org.springframework.stereotype.Component;

import com.devspace.conexfy.entities.ConConnectionEntity;
import com.devspace.conexfy.models.DevEncryptedString;
import com.devspace.conexfy.utils.DevObjectMapperUtil;

import reactor.core.publisher.Mono;

/**
 * Procesador para manejar la post-autenticación de conexiones OAuth2.
 * Implementa la interfaz {@link ConAuthPostProcessor} para procesar la respuesta de autenticación
 * y actualizar la entidad de conexión con el token de acceso.
 */
@Component
public class ConOAuth2PostProcessor implements ConAuthPostProcessor {

    private final DevObjectMapperUtil devObjectMapperUtil;

    public ConOAuth2PostProcessor(DevObjectMapperUtil devObjectMapperUtil) {
        this.devObjectMapperUtil = devObjectMapperUtil;
    }
    /**
     * Procesa la respuesta de autenticación de OAuth2 y actualiza la entidad de conexión.
     *
     * @param conn La entidad de conexión a actualizar.
     * @param authResponseBody El cuerpo de la respuesta de autenticación.
     * @return Mono con la entidad de conexión actualizada.
     */
    @Override
    public Mono<ConConnectionEntity> apply(ConConnectionEntity conn, String authResponseBody) {
        // Aquí puedes implementar la lógica específica para procesar el post-autenticación de OAuth2
        // Por ejemplo, extraer el token de acceso del cuerpo de la respuesta y almacenarlo en la entidad de conexión
        String token = (String) devObjectMapperUtil.parseJson(authResponseBody).get("token");
        conn.setAuthToken(new DevEncryptedString(token));
        return Mono.just(conn);
    }

}
