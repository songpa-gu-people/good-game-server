package people.songpagu.goodgame.application.guild.service

import people.songpagu.goodgame.application.guild.incoming.GuildCreateUseCase
import people.songpagu.goodgame.application.guild.incoming.GuildCreateUseCase.GuildCreateCommand
import people.songpagu.goodgame.application.guild.outgoing.GuildCreatePort

class GuildCreateService(
    private val guildCreatePort: GuildCreatePort
) : GuildCreateUseCase {
    override fun createGuild(guildCreateCommand: GuildCreateCommand) {
        guildCreatePort.create(guildCreateCommand)
    }
}