package people.songpagu.goodgame.security.config

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.http.HttpStatus
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.HttpStatusEntryPoint
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import people.songpagu.goodgame.application.token.decode.incoming.TokenAuthenticateUseCase
import people.songpagu.goodgame.security.config.filter.JwtAuthenticationFilter
import people.songpagu.goodgame.security.config.properties.GoodGameRedirectUriProperty
import people.songpagu.goodgame.security.domain.member.service.UserDetailsServiceImpl
import people.songpagu.goodgame.security.domain.member.service.WebSecurityUserService
import people.songpagu.goodgame.security.domain.oauth.handler.OAuth2FailHandler
import people.songpagu.goodgame.security.domain.oauth.handler.OAuth2SuccessHandler
import people.songpagu.infrastructure.log.Slf4j
import people.songpagu.infrastructure.log.Slf4j.Companion.log
import javax.annotation.PostConstruct
import javax.servlet.Filter

@Import(
    value = [
        GoodGameConfigManager::class,
        GoodGameCorsConfig::class
    ]
)
@Configuration
@EnableConfigurationProperties(
    value = [
        GoodGameRedirectUriProperty::class
    ]
)
@ComponentScan("people.songpagu.goodgame.security.domain")
class GoodGameSecurityConfig {

    @Slf4j
    @Configuration
    @EnableWebSecurity
    @ConditionalOnProperty(value = ["goodgame.security.mode"], havingValue = "web-security")
    class SecurityMajorConfig {

        @PostConstruct
        fun setup() {
            log.info("GoodGame SecurityConfig enabled.")
        }

        @Bean
        fun securityFilterChain(
            http: HttpSecurity,
            oAuth2SuccessHandler: OAuth2SuccessHandler,
            oAuth2FailHandler: OAuth2FailHandler,
            webSecurityUserService: WebSecurityUserService,
            authorizationRequestRepository: AuthorizationRequestRepository<OAuth2AuthorizationRequest>,
            tokenAuthenticateUseCase: TokenAuthenticateUseCase,
            userDetailsService: UserDetailsServiceImpl
        ): SecurityFilterChain {
            http.headers()
                .frameOptions().disable()
                .and()
                .csrf().disable()
                .cors().and()
                .authorizeRequests()
                .anyRequest().permitAll()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))

                .and()
                .oauth2Login()
                .authorizationEndpoint()
                .authorizationRequestRepository(authorizationRequestRepository)
                .and()
                .userInfoEndpoint().userService(webSecurityUserService)

                .and()
                .successHandler(oAuth2SuccessHandler)
                .failureHandler(oAuth2FailHandler)
                .permitAll()
                .and()
                .addFilterBefore(
                    jwtAuthenticationFilter(tokenAuthenticateUseCase, userDetailsService),
                    UsernamePasswordAuthenticationFilter::class.java
                )

            return http.build()
        }

        fun jwtAuthenticationFilter(
            tokenAuthenticateUseCase: TokenAuthenticateUseCase,
            userDetailsService: UserDetailsServiceImpl
        ): Filter? {
            return JwtAuthenticationFilter(tokenAuthenticateUseCase, userDetailsService)
        }
    }

    @Slf4j
    @Configuration
    @EnableWebSecurity
    @ConditionalOnProperty(value = ["goodgame.security.mode"], havingValue = "stub")
    class SecurityStubConfig {

        @PostConstruct
        fun setup() {
            log.debug("stub GoodGame SecurityConfig enabled.")
        }

        @Bean
        fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
            http.antMatcher("/**").csrf().disable().cors().disable().authorizeRequests().anyRequest().permitAll()

            return http.build()
        }
    }
}
