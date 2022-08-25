package people.songpagu.goodgame.api.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import people.songpagu.goodgame.application.matching.option.incoming.MatchingOptionFindUseCase
import people.songpagu.goodgame.application.matching.option.outgoing.MatchingOptionFindPort
import people.songpagu.goodgame.application.matching.option.service.MatchingOptionFindService
import people.songpagu.goodgame.application.member.auth.login.incoming.LoginMemberNumberFindUseCase
import people.songpagu.goodgame.application.member.auth.login.outgoing.LoginMemberNumberFindPort
import people.songpagu.goodgame.application.member.auth.login.service.LoginMemberNumberFindService
import people.songpagu.goodgame.application.token.decode.incoming.TokenAuthenticateUseCase
import people.songpagu.goodgame.application.token.decode.outgoing.TokenDecodePort
import people.songpagu.goodgame.application.token.decode.service.TokenAuthenticateService
import people.songpagu.goodgame.jpa.config.GoodGameJpaConfig
import people.songpagu.goodgame.security.config.GoodGameSecurityConfig

@ComponentScan(
    basePackages = [
        "people.songpagu.goodgame.api.domain",
        "people.songpagu.goodgame.api.config",
    ]
)
@Import(
    value = [
        GoodGameWebConfig::class,
        GoodGameJpaConfig::class,
        GoodGameSecurityConfig::class
    ]
)
@Configuration
class GoodGameApiConfig {
    @Bean
    fun tokenAuthenticateUseCase(tokenDecodePort: TokenDecodePort): TokenAuthenticateUseCase {
        return TokenAuthenticateService(tokenDecodePort)
    }

    @Bean
    fun loginMemberNumberFindUseCase(port: LoginMemberNumberFindPort): LoginMemberNumberFindUseCase {
        return LoginMemberNumberFindService(port)
    }

    @Bean
    fun matchingOptionFindUseCase(port: MatchingOptionFindPort): MatchingOptionFindUseCase {
        return MatchingOptionFindService(port)
    }
}
