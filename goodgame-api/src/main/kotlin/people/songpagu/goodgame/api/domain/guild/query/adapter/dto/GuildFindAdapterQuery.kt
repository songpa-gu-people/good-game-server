package people.songpagu.goodgame.api.domain.guild.query.adapter.dto

sealed class GuildFindAdapterQuery(
    val size: Long,
) {
    data class More(val startId: Long?, private val _size: Long) : GuildFindAdapterQuery(size = _size)

    data class Name(val name: String, private val _size: Long) : GuildFindAdapterQuery(size = _size)

    companion object {
        fun of(startId: Long?, size: Long, guildName: String?): GuildFindAdapterQuery {
            return if (guildName == null) {
                More(startId = startId, _size = size)
            } else {
                Name(name = guildName, _size = size)
            }
        }
    }
}
