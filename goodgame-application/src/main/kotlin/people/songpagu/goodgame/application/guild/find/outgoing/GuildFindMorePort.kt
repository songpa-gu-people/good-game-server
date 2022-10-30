package people.songpagu.goodgame.application.guild.find.outgoing

import people.songpagu.goodgame.application.guild.find.outgoing.dto.GuildFindQueryAnswer

interface GuildFindMorePort {
    fun findMoreBy(startId: Long?, size: Long, condition: GuildFindQueryCondition): GuildFindMoreQueryAnswerCollection

    data class GuildFindQueryCondition(
        val name: String? = null,
    )

    data class GuildFindMoreQueryAnswerCollection(
        val contents: List<GuildFindQueryAnswer>,
    ) {
        val lastId: Long?
            get() = contents.maxOfOrNull { it.guildId }
    }

}
