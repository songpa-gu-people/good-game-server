package people.songpagu.infrastructure.jwt.config

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import people.songpagu.infrastructure.jwt.config.properties.GoodGameJwtProperties
import people.songpagu.infrastructure.jwt.incoming.jjwt.JJwtTokenDecoder
import people.songpagu.infrastructure.jwt.incoming.jjwt.JJwtTokenGenerateUseCase
import people.songpagu.infrastructure.jwt.incoming.JwtTokenDecodeUseCase
import people.songpagu.infrastructure.jwt.incoming.JwtTokenGenerateUseCase

@Configuration
@EnableConfigurationProperties(
    value = [
        GoodGameJwtProperties::class,
    ]
)
class GoodGameJwtConfig {

    @Configuration
    @ConditionalOnProperty(name = ["goodgame.jwt.mode"], havingValue = "jjwt")
    class JJwtConfig(
        private val properties: GoodGameJwtProperties,
    ) {
        @Bean
        fun jjwtTokenGenerator(): JwtTokenGenerateUseCase {
            return JJwtTokenGenerateUseCase(properties.jjwtKey)
        }

        @Bean
        fun jjwtTokenDecoder(): JwtTokenDecodeUseCase {
            return JJwtTokenDecoder(properties.jjwtKey)
        }
    }
}
