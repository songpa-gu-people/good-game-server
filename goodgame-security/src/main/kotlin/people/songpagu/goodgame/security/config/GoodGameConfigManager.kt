package people.songpagu.goodgame.security.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import people.songpagu.goodgame.application.member.auth.join.incoming.MemberJoinUseCase
import people.songpagu.goodgame.application.member.auth.join.outgoing.LoginHistoryRecordPort
import people.songpagu.goodgame.application.member.auth.join.outgoing.MemberCreatePort
import people.songpagu.goodgame.application.member.auth.join.outgoing.MemberFindPort
import people.songpagu.goodgame.application.member.auth.join.service.MemberJoinService
import people.songpagu.goodgame.application.member.auth.login.incoming.LoginTokenCreateUseCase
import people.songpagu.goodgame.application.member.auth.login.incoming.LoginTokenRemoveUseCase
import people.songpagu.goodgame.application.member.auth.login.outgoing.LoginTokenCreatePort
import people.songpagu.goodgame.application.member.auth.login.outgoing.LoginTokenFindPort
import people.songpagu.goodgame.application.member.auth.login.outgoing.LoginTokenRemovePort
import people.songpagu.goodgame.application.member.auth.login.service.LoginTokenCreateService
import people.songpagu.goodgame.application.member.auth.login.service.LoginTokenRemoveService
import people.songpagu.goodgame.application.token.generate.incoming.TokenGenerateUseCase
import people.songpagu.goodgame.application.token.generate.outgoing.TokenGeneratePort
import people.songpagu.goodgame.application.token.generate.service.TokenGenerateService
import people.songpagu.goodgame.jpa.config.GoodGameJpaConfig
import people.songpagu.goodgame.security.config.properties.GoodGameRedirectUriProperty
import people.songpagu.infrastructure.jwt.config.GoodGameJwtConfig

@Import(
    value = [
        GoodGameJpaConfig::class,
        GoodGameJwtConfig::class,
        GoodGameRedirectUriProperty::class,
    ]
)
@Configuration
class GoodGameConfigManager {
    @Bean
    fun tokenGenerateUseCase(tokenGeneratePort: TokenGeneratePort): TokenGenerateUseCase {
        return TokenGenerateService(tokenGeneratePort)
    }

    @Bean
    fun loginTokenCreateUseCase(loginTokenCreatePort: LoginTokenCreatePort): LoginTokenCreateUseCase {
        return LoginTokenCreateService(loginTokenCreatePort)
    }

    @Bean
    fun loginTokenRemoveUseCase(
        loginTokenRemovePort: LoginTokenRemovePort,
        loginTokenFindPort: LoginTokenFindPort,
    ): LoginTokenRemoveUseCase {
        return LoginTokenRemoveService(loginTokenFindPort, loginTokenRemovePort)
    }

    @Bean
    fun memberJoinUseCase(
        memberFindPort: MemberFindPort,
        memberCreatePort: MemberCreatePort,
        loginHistoryRecordPort: LoginHistoryRecordPort,
    ): MemberJoinUseCase {
        return MemberJoinService(
            memberFindPort = memberFindPort,
            memberCreatePort = memberCreatePort,
            loginHistoryRecordPort = loginHistoryRecordPort,
        )
    }
}
