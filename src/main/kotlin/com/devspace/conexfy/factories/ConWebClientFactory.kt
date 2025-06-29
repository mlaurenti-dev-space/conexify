package com.devspace.conexfy.factories

import com.devspace.conexfy.entities.ConConnectionEntity
import io.netty.channel.ChannelOption
import io.netty.handler.timeout.ReadTimeoutHandler
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.Connection
import reactor.netty.http.client.HttpClient
import java.util.function.Consumer

@Component
class ConWebClientFactory(private val webClientBuilder: WebClient.Builder) {
    fun getClientFor(conConnectionEntity: ConConnectionEntity): WebClient {
        val builder = webClientBuilder.build().mutate()

        builder.baseUrl(conConnectionEntity.url)

        // Timeouts (connect + read) usando Reactor Netty
        /**
         * 1) Timeout de conexión: cuánto esperar para establecer el TCP
         * 2) Timeout de lectura: cuánto esperar para recibir una respuesta después de enviar la solicitud
         * ReadTimeoutHandler espera conn.getReadTimeoutMs() milisegundos
         * si no llega ni un byte en ese intervalo, lanza una excepción
         * De ese modo todo lo que hagas con ese WebClient particular tendrá:
         * - Un tope para abrir la conexión al host remoto.
         * - Un tope para “leer” la respuesta, protegiéndote de respuestas muy lentas o sockets que se quedan abiertos sin enviar datos.
         */
        val httpClient = HttpClient.create().followRedirect(true)
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, conConnectionEntity.connectTimeoutMs)
            .doOnConnected(Consumer { ch: Connection? ->
                ch!!.addHandlerLast(
                    ReadTimeoutHandler(conConnectionEntity.readTimeoutMs / 1000)
                )
            })
        builder.clientConnector(ReactorClientHttpConnector(httpClient))


        // Headers
        // if (conConnectionEntity.getHeadersJson() != null && !conConnectionEntity.getHeadersJson().isEmpty()) {
        //     builder.defaultHeaders(h -> conConnectionEntity.getHeadersJson().forEach(h::add));
        // }
        return builder.build()
    }
}