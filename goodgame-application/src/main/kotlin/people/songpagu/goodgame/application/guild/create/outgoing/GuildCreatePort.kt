package people.songpagu.goodgame.application.guild.create.outgoing

import people.songpagu.goodgame.application.guild.create.incoming.GuildCreateUseCase.GuildCreateCommand

interface GuildCreatePort {
    fun create(guildCreateCommand: GuildCreateCommand)
}
