package people.songpagu.goodgame.api.config.guild

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import people.songpagu.goodgame.application.guild.create.incoming.GuildCreateUseCase
import people.songpagu.goodgame.application.guild.create.outgoing.GuildCreatePort
import people.songpagu.goodgame.application.guild.create.service.GuildCreateService
import people.songpagu.goodgame.application.guild.find.incoming.GuildFindMoreUseCase
import people.songpagu.goodgame.application.guild.find.incoming.GuildNameFindUseCase
import people.songpagu.goodgame.application.guild.find.outgoing.GuildFindMorePort
import people.songpagu.goodgame.application.guild.find.outgoing.GuildNameFindPort
import people.songpagu.goodgame.application.guild.find.service.GuildFindMoreService
import people.songpagu.goodgame.application.guild.find.service.GuildNameFindService

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
        guildFindMorePort: GuildFindMorePort,
    ): GuildFindMoreUseCase {
        return GuildFindMoreService(guildFindMorePort)
    }

    @Bean
    fun guildNameFindUseCase(
        guildNameFindPort: GuildNameFindPort,
    ): GuildNameFindUseCase {
        return GuildNameFindService(guildNameFindPort)
    }
}
