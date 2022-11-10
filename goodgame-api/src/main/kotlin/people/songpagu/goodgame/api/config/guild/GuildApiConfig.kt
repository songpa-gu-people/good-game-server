package people.songpagu.goodgame.api.config.guild

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import people.songpagu.goodgame.application.guild.create.incoming.GuildCreateUseCase
import people.songpagu.goodgame.application.guild.create.outgoing.GuildCreatePort
import people.songpagu.goodgame.application.guild.create.service.GuildCreateService
import people.songpagu.goodgame.application.guild.find.incoming.GuildFindMoreUseCase
import people.songpagu.goodgame.application.guild.find.outgoing.GuildListFindPort
import people.songpagu.goodgame.application.guild.find.service.GuildFindMoreService

@Configuration
class GuildApiConfig {
    @Bean
    fun guildCreateUseCase(
        guildCreatePort: GuildCreatePort
    ): GuildCreateUseCase {
        return GuildCreateService(guildCreatePort)
    }

    @Bean
    fun guildFindMoreUseCase(
        guildListFindPort: GuildListFindPort,
    ): GuildFindMoreUseCase {
        return GuildFindMoreService(guildListFindPort)
    }

}
