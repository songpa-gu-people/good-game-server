package people.songpagu.goodgame.application.guild.find.outgoing

import people.songpagu.goodgame.application.guild.find.outgoing.dto.GuildFindQueryAnswer

interface GuildListFindPort {
    fun findMoreBy(
        condition: GuildFindQueryCondition,
        pageNumber: Int,
        pageSize: Int
    ): GuildFindMoreQueryAnswerCollection

    data class GuildFindQueryCondition(
        val name: String? = null,
    )

    data class GuildFindMoreQueryAnswerCollection(
        val contents: List<GuildFindQueryAnswer>,
        val totalPageSize: Int,
    )

}
