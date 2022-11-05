package people.songpagu.goodgame.api.domain.guild.query.adapter

import people.songpagu.goodgame.api.config.common.response.PageResponse
import people.songpagu.goodgame.api.config.layer.Adapter
import people.songpagu.goodgame.api.domain.guild.query.adapter.dto.GuildFindAdapterAnswer
import people.songpagu.goodgame.api.domain.guild.query.adapter.dto.GuildFindAdapterQuery
import people.songpagu.goodgame.application.guild.find.incoming.GuildFindMoreUseCase

@Adapter(readOnly = true)
class GuildFindAdapter(
    private val guildFindMoreUseCase: GuildFindMoreUseCase,
) {
    fun find(query: GuildFindAdapterQuery): GuildFindAdapterAnswer {
        return guildFindMoreUseCase.findMoreBy(
            name = query.name,
            pageNumber = query.pageNumber,
            pageSize = query.pageSize
        )
            .let {
                GuildFindAdapterAnswer(
                    contents = it.contents,
                    PageResponse(totalPageSize = it.totalPageSize, currentPage = query.pageNumber)
                )
            }
    }
}
