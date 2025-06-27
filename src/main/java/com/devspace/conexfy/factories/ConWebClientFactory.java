package com.devspace.conexfy.factories;

import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.devspace.conexfy.entities.ConConnectionEntity;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import reactor.netty.http.client.HttpClient;

@Component
public class ConWebClientFactory {
    
    private final WebClient.Builder webClientBuilder;

    public ConWebClientFactory(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    public WebClient getClientFor(ConConnectionEntity conConnectionEntity) {
        var builder = webClientBuilder.build().mutate();
        
        if (conConnectionEntity.getUrl() != null) {
            builder.baseUrl(conConnectionEntity.getUrl());
        }

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
        if (conConnectionEntity.getReadTimeoutMs() != null && conConnectionEntity.getConnectTimeoutMs() != null) {
            HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, conConnectionEntity.getConnectTimeoutMs())
                .doOnConnected(ch -> ch.addHandlerLast(
                    new ReadTimeoutHandler(conConnectionEntity.getReadTimeoutMs() / 1000)
                ));
            builder.clientConnector(new ReactorClientHttpConnector(httpClient));
        }
        
        // Headers
        // if (conConnectionEntity.getHeadersJson() != null && !conConnectionEntity.getHeadersJson().isEmpty()) {
        //     builder.defaultHeaders(h -> conConnectionEntity.getHeadersJson().forEach(h::add));
        // }
        
        return builder.build();
    }
}