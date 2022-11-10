package people.songpagu.goodgame.application.guild.find.incoming

import people.songpagu.goodgame.application.guild.find.incoming.dto.GuildFindContent

interface GuildFindMoreUseCase {
    fun findMoreBy(name: String?, pageNumber: Int, pageSize: Int): GuildFindAnswer

    data class GuildFindAnswer(
        val contents: List<GuildFindContent>,
        val totalPageSize: Int
    )
}
