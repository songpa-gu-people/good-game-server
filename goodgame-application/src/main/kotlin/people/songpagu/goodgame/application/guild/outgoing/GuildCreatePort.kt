package people.songpagu.goodgame.application.guild.outgoing

import people.songpagu.goodgame.application.guild.incoming.GuildCreateUseCase

interface GuildCreatePort {
    fun create(guildCreateCommand: GuildCreateUseCase.GuildCreateCommand)
}