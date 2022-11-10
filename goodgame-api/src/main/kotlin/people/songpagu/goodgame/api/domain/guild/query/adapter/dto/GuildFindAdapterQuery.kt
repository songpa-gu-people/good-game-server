package people.songpagu.goodgame.api.domain.guild.query.adapter.dto

data class GuildFindAdapterQuery(
    val name: String? = null,
    val pageNumber: Int,
    val pageSize: Int,
)
