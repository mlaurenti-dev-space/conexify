package com.devspace.conexfy.configurations

import com.devspace.conexfy.converters.*
import io.r2dbc.spi.ConnectionFactory
import org.jasypt.encryption.StringEncryptor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.converter.Converter
import org.springframework.data.domain.ReactiveAuditorAware
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions
import org.springframework.data.r2dbc.dialect.PostgresDialect
import org.springframework.lang.NonNull
import reactor.core.publisher.Mono
import java.util.List

@Configuration
@EnableR2dbcAuditing
open class DevR2dbcConfig(private val cf: ConnectionFactory, private val encryptor: StringEncryptor) :
    AbstractR2dbcConfiguration() {
    @NonNull
    override fun connectionFactory(): ConnectionFactory {
        return cf
    }

    @Bean
    @NonNull
    override fun r2dbcCustomConversions(): R2dbcCustomConversions {
        return R2dbcCustomConversions.of(
            PostgresDialect.INSTANCE,
            listOf(
                ConAuthTypeEnumToStringWritingConverter(),
                ConHttpMethodEnumToStringWritingConverter(),
                ConStringToConAuthTypeEnumReadingConverter(),
                ConStringToConHttpMethodEnumReadingConverter(),
                DevMapToJsonWritingConverter(),
                DevJsonToMapReadingConverter(),
                DevEncryptWritingConverter(encryptor),
                DevDecryptReadingConverter(encryptor)
            )
        )
    }

    /** TODO: Implmentar usuario que crea y modifica  */
    @Bean
    open fun auditorAware(): ReactiveAuditorAware<String> {
        return ReactiveAuditorAware { Mono.empty() }
    }
}
