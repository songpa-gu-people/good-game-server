package people.songpagu.goodgame.security.config

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.HttpStatusEntryPoint
import people.songpagu.goodgame.security.domain.member.service.WebSecurityUserService
import people.songpagu.goodgame.security.domain.oauth.handler.OAuth2SuccessHandler
import people.songpagu.infrastructure.log.Slf4j
import people.songpagu.infrastructure.log.Slf4j.Companion.log
import javax.annotation.PostConstruct

@Configuration
class GoodGameSecurityConfig {

    @Slf4j
    @Configuration
    @EnableWebSecurity
    @ComponentScan("people.songpagu.goodgame.security.domain")
    @ConditionalOnProperty(value = ["goodgame.security.mode"], havingValue = "web-security")
    class SecurityMajorConfig(
        private val webSecurityUserService: WebSecurityUserService,
    ) {

        @PostConstruct
        fun setup() {
            log.info("GoodGame SecurityConfig enabled.")
        }

        @Bean
        fun securityFilterChain(
            http: HttpSecurity,
            oAuth2SuccessHandler: OAuth2SuccessHandler,
        ): SecurityFilterChain {
            http
                .headers().frameOptions().disable().and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/login", "/h2-console/**", "/static/**").permitAll()
                .anyRequest().authenticated()
                .and().exceptionHandling().authenticationEntryPoint(HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                .and().oauth2Login()
                .successHandler(oAuth2SuccessHandler)
                .userInfoEndpoint()
                .userService(webSecurityUserService)

            return http.build()
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
            http.antMatcher("/**").csrf().disable()
                .cors().disable()
                .authorizeRequests().anyRequest().permitAll()

            return http.build()
        }
    }
}
