package people.songpagu.goodgame.api.config

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.format.FormatterRegistry
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import people.songpagu.goodgame.api.config.common.FormatResourceFactory
import people.songpagu.goodgame.api.config.interceptor.AuthenticationInterceptor
import people.songpagu.goodgame.api.domain.token.AccessTokenControllerPath

@Configuration
@EnableConfigurationProperties(value = [AuthenticationProperties::class])
class GoodGameWebConfig(
    private val authenticationProperties: AuthenticationProperties,
    private val authenticationInterceptor: AuthenticationInterceptor,
) : WebMvcConfigurer {
    @Bean
    fun jsonCustomizer(): Jackson2ObjectMapperBuilderCustomizer {
        return FormatResourceFactory.jsonCustomizer
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        if (authenticationProperties.enabled) {
            registry.addInterceptor(authenticationInterceptor)
                .addPathPatterns(authenticationProperties.urlPatterns)
                .excludePathPatterns(AccessTokenControllerPath.getAccessToken)
        }
    }

    override fun addFormatters(registry: FormatterRegistry) {
        for (parameterBinder in FormatResourceFactory.parameterBinders) {
            registry.addConverter(parameterBinder)
        }
    }

}

@ConstructorBinding
@ConfigurationProperties(prefix = "authentication")
data class AuthenticationProperties(
    val enabled: Boolean,
    val urlPatterns: List<String> = emptyList(),
)
