package people.songpagu.goodgame.application.guild.find.incoming

import people.songpagu.goodgame.application.guild.find.incoming.dto.GuildFindContent

interface GuildFindMoreUseCase {
    fun findMoreBy(startId: Long?, size: Long, name: String?): GuildFindAnswer

    data class GuildFindAnswer(
        val contents: List<GuildFindContent>,
        val lastId: Long?,
    )
}
