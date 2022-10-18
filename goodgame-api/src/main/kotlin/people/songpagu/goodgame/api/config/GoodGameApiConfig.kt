package people.songpagu.goodgame.api.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import people.songpagu.goodgame.application.guild.create.incoming.GuildCreateUseCase
import people.songpagu.goodgame.application.guild.create.outgoing.GuildCreatePort
import people.songpagu.goodgame.application.guild.create.service.GuildCreateService
import people.songpagu.goodgame.application.matching.option.incoming.MatchingOptionFindUseCase
import people.songpagu.goodgame.application.matching.option.incoming.MatchingOptionSaveUseCase
import people.songpagu.goodgame.application.matching.option.outgoing.MatchingOptionFindPort
import people.songpagu.goodgame.application.matching.option.outgoing.UpdateMatchingOptionStatePort
import people.songpagu.goodgame.application.matching.option.service.MatchingOptionFindService
import people.songpagu.goodgame.application.matching.option.service.MatchingOptionSaveService
import people.songpagu.goodgame.application.member.auth.login.incoming.LoginMemberNumberFindUseCase
import people.songpagu.goodgame.application.member.auth.login.outgoing.LoginMemberNumberFindPort
import people.songpagu.goodgame.application.member.auth.login.service.LoginMemberNumberFindService
import people.songpagu.goodgame.application.member.privacy.incoming.MemberPrivacyFindUseCase
import people.songpagu.goodgame.application.member.privacy.outgoing.MemberPrivacyFindPort
import people.songpagu.goodgame.application.member.privacy.service.MemberPrivacyFindService
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
    fun loginMemberNumberFindUseCase(port: LoginMemberNumberFindPort): LoginMemberNumberFindUseCase {
        return LoginMemberNumberFindService(port)
    }

    @Bean
    fun matchingOptionFindUseCase(port: MatchingOptionFindPort): MatchingOptionFindUseCase {
        return MatchingOptionFindService(port)
    }

    @Bean
    fun matchingOptionSaveUseCase(
        updateMatchingOptionStatePort: UpdateMatchingOptionStatePort,
        matchingOptionFindPort: MatchingOptionFindPort,
    ): MatchingOptionSaveUseCase {
        return MatchingOptionSaveService(updateMatchingOptionStatePort, matchingOptionFindPort)
    }

    @Bean
    fun memberPrivacyFindUseCase(
        memberPrivacyFindPort: MemberPrivacyFindPort
    ): MemberPrivacyFindUseCase {
        return MemberPrivacyFindService(memberPrivacyFindPort)
    }

    @Bean
    fun guildCreateUseCase(
        guildCreatePort: GuildCreatePort
    ): GuildCreateUseCase {
        return GuildCreateService(guildCreatePort)
    }
}
