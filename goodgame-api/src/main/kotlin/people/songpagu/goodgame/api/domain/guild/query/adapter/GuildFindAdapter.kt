package people.songpagu.goodgame.api.domain.guild.query.adapter

import people.songpagu.goodgame.api.config.layer.Adapter
import people.songpagu.goodgame.api.domain.guild.query.adapter.dto.GuildFindAdapterAnswer
import people.songpagu.goodgame.api.domain.guild.query.adapter.dto.GuildFindAdapterQuery
import people.songpagu.goodgame.application.guild.find.incoming.GuildFindMoreUseCase

@Adapter(readOnly = true)
class GuildFindAdapter(
    private val guildFindMoreUseCase: GuildFindMoreUseCase,
) {
    fun find(query: GuildFindAdapterQuery): GuildFindAdapterAnswer {
        return guildFindMoreUseCase.findMoreBy(startId = query.startId, size = query.size, name = query.name)
            .let { GuildFindAdapterAnswer(lastId = it.lastId, contents = it.contents) }
    }
}
