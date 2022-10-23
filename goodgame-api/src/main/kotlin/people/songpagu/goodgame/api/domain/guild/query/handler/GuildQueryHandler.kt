package people.songpagu.goodgame.api.domain.guild.query.handler

import people.songpagu.goodgame.api.config.layer.Handler
import people.songpagu.goodgame.api.domain.guild.query.adapter.GuildFindAdapter
import people.songpagu.goodgame.api.domain.guild.query.adapter.dto.GuildFindAdapterAnswer
import people.songpagu.goodgame.api.domain.guild.query.adapter.dto.GuildFindAdapterQuery

@Handler
class GuildQueryHandler(
    private val adapter: GuildFindAdapter,
) {
    fun findBy(startId: Long?, size: Long, guildName: String?): GuildFindAdapterAnswer {
        val query = GuildFindAdapterQuery.of(startId, size, guildName)
        return adapter.find(query)
    }
}
