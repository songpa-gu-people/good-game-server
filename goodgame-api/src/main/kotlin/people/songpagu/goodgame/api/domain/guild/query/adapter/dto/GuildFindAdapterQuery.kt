package people.songpagu.goodgame.api.domain.guild.query.adapter.dto

data class GuildFindAdapterQuery(
    val startId: Long?,
    val size: Long,
    val name: String? = null,
)
