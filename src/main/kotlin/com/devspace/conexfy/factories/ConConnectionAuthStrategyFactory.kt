package com.devspace.conexfy.factories

import com.devspace.conexfy.enums.ConAuthTypeEnum
import com.devspace.conexfy.strategies.auths.ConConnectionAuthStrategy
import com.devspace.conexfy.strategies.auths.ConConnectionOAuth2Strategy
import org.springframework.stereotype.Component
import java.util.*
import java.util.Map

/**
 * Factory for creating authentication strategies for connections.
 *
 * This factory provides a way to retrieve the appropriate authentication strategy
 * based on the specified authentication type.
 */
@Component
class ConConnectionAuthStrategyFactory(private val conConnectionOAuth2Strategy: ConConnectionOAuth2Strategy) {
    private val strategies: MutableMap<ConAuthTypeEnum, ConConnectionAuthStrategy> = Map.of<ConAuthTypeEnum, ConConnectionAuthStrategy>(
        ConAuthTypeEnum.OAUTH2, this.conConnectionOAuth2Strategy
    )

    fun getStrategy(authType: ConAuthTypeEnum): Optional<ConConnectionAuthStrategy> {
        if (ConAuthTypeEnum.NO_AUTH == authType) return Optional.empty<ConConnectionAuthStrategy>() // No auth needed

        val strategy = strategies[authType]
        if (strategy != null) {
            return Optional.of<ConConnectionAuthStrategy>(strategy)
        }

        throw IllegalArgumentException("Unsupported auth type: $authType")
    }
}
