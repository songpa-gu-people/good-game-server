package people.songpagu.goodgame.api.domain.guild.query.handler

import people.songpagu.goodgame.api.config.layer.Handler
import people.songpagu.goodgame.api.domain.guild.query.adapter.GuildFindAdapter
import people.songpagu.goodgame.api.domain.guild.query.adapter.dto.GuildFindAdapterAnswer
import people.songpagu.goodgame.api.domain.guild.query.adapter.dto.GuildFindAdapterQuery
import people.songpagu.goodgame.api.domain.guild.query.controller.request.GuildFindMoreRequest

@Handler
class GuildQueryHandler(
    private val adapter: GuildFindAdapter,
) {
    fun findBy(guildFindMoreRequest: GuildFindMoreRequest): GuildFindAdapterAnswer {
        val query = GuildFindAdapterQuery(
            name = guildFindMoreRequest.guildName,
            pageNumber = guildFindMoreRequest.pageNumber,
            pageSize = guildFindMoreRequest.size,
        )

        return adapter.find(query)
    }
}
