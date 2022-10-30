package people.songpagu.goodgame.application.guild.create.service

import people.songpagu.goodgame.application.guild.create.incoming.GuildCreateUseCase
import people.songpagu.goodgame.application.guild.create.incoming.GuildCreateUseCase.GuildCreateCommand
import people.songpagu.goodgame.application.guild.create.outgoing.GuildCreatePort

class GuildCreateService(
    private val guildCreatePort: GuildCreatePort
) : GuildCreateUseCase {
    override fun createGuild(guildCreateCommand: GuildCreateCommand) {
        guildCreatePort.create(guildCreateCommand)
    }
}
