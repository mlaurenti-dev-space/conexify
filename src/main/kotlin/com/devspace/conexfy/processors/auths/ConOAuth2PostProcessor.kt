package com.devspace.conexfy.processors.auths

import com.devspace.conexfy.entities.ConConnectionEntity
import com.devspace.conexfy.models.DevEncryptedString
import com.devspace.conexfy.utils.DevObjectMapperUtil
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

/**
 * Procesador para manejar la post-autenticación de conexiones OAuth2.
 * Implementa la interfaz [ConAuthPostProcessor] para procesar la respuesta de autenticación
 * y actualizar la entidad de conexión con el token de acceso.
 */
@Component
class ConOAuth2PostProcessor(private val devObjectMapperUtil: DevObjectMapperUtil) : ConAuthPostProcessor {
    /**
     * Procesa la respuesta de autenticación de OAuth2 y actualiza la entidad de conexión.
     *
     * @param conn La entidad de conexión a actualizar.
     * @param authResponseBody El cuerpo de la respuesta de autenticación.
     * @return Mono con la entidad de conexión actualizada.
     */
    override fun apply(conn: ConConnectionEntity, authResponseBody: String): Mono<ConConnectionEntity> {
        // Aquí puedes implementar la lógica específica para procesar el post-autenticación de OAuth2
        // Por ejemplo, extraer el token de acceso del cuerpo de la respuesta y almacenarlo en la entidad de conexión
        val token = devObjectMapperUtil.parseJson(authResponseBody)!!["token"] as String
        conn.authToken = DevEncryptedString(token)
        return Mono.just(conn)
    }
}
