package people.songpagu.goodgame.application.guild.find.incoming

interface GuildFindMoreUseCase {
    fun findMoreBy(query: GuildFindMoreQuery): GuildFindAnswer

    data class GuildFindMoreQuery(
        val startId: Long?,
        val size: Long,
    )

    data class GuildFindAnswer(
        val contents: List<Content>,
        val lastId: Long?,
    ) {
        data class Content(
            val guildId: Long,
            val guildNumber: String,
            val guildMemberSize: Long,
            val guildName: String,
        )
    }
}
