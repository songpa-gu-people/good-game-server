package people.songpagu.goodgame.application.guild.find.incoming

import people.songpagu.goodgame.application.guild.find.incoming.dto.GuildFindContent

interface GuildNameFindUseCase {
    fun findByName(guildName: String, size: Long): GuildNameFindAnswer

    data class GuildNameFindAnswer(
        val contents: List<GuildFindContent>
    )
}
