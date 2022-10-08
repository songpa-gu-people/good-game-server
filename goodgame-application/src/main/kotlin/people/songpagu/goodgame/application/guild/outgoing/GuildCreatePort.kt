package people.songpagu.goodgame.application.guild.outgoing

import people.songpagu.goodgame.application.guild.incoming.GuildCreateUseCase.GuildCreateCommand

interface GuildCreatePort {
    fun create(guildCreateCommand: GuildCreateCommand)
}