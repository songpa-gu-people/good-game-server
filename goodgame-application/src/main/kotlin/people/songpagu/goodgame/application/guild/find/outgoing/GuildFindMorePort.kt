package people.songpagu.goodgame.application.guild.find.outgoing

interface GuildFindMorePort {
    fun findMoreBy(startId: Long?, size: Long): GuildFindMoreQueryAnswerCollection

    data class GuildFindMoreQueryAnswerCollection(
        val contents: List<GuildFindMoreQueryAnswer>,
    ) {
        val lastId: Long?
            get() = contents.maxOfOrNull { it.guildId }
    }

    data class GuildFindMoreQueryAnswer(
        val guildId: Long,
        val guildNumber: String,
        val guildMemberSize: Long,
        val guildName: String,
    )
}
