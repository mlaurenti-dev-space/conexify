package com.devspace.conexfy.configurations;

import java.util.List;
import java.util.Optional;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.domain.ReactiveAuditorAware;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions;
import org.springframework.data.r2dbc.dialect.PostgresDialect;

import com.devspace.conexfy.converters.DevDecryptReadingConverter;
import com.devspace.conexfy.converters.DevEncryptWritingConverter;
import com.devspace.conexfy.converters.DevJsonToMapReadingConverter;
import com.devspace.conexfy.converters.DevMapToJsonWritingConverter;

import io.r2dbc.spi.ConnectionFactory;
import reactor.core.publisher.Mono;

@Configuration
@EnableR2dbcAuditing
public class DevR2dbcConfig extends AbstractR2dbcConfiguration {

    private final ConnectionFactory cf;
    private final StringEncryptor encryptor;

    public DevR2dbcConfig(ConnectionFactory cf, StringEncryptor encryptor) {
        this.cf = cf;
        this.encryptor = encryptor;
    }

    @Override
    public ConnectionFactory connectionFactory() {
        return cf;
    }

    @Bean
    @Override
    public R2dbcCustomConversions r2dbcCustomConversions() {
        return R2dbcCustomConversions.of(
                PostgresDialect.INSTANCE,
                List.of(
                        new DevMapToJsonWritingConverter(),
                        new DevJsonToMapReadingConverter(),
                        new DevEncryptWritingConverter(encryptor),
                        new DevDecryptReadingConverter(encryptor)));
    }

    /** TODO: Implmentar usuario que crea y modifica */
    @Bean
    public ReactiveAuditorAware<String> auditorAware() {
        return () -> Mono.empty();
    }
}
