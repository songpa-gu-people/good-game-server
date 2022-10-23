package people.songpagu.goodgame.api.domain.guild.query.adapter

import people.songpagu.goodgame.api.config.layer.Adapter
import people.songpagu.goodgame.api.domain.guild.query.adapter.dto.GuildFindAdapterAnswer
import people.songpagu.goodgame.api.domain.guild.query.adapter.dto.GuildFindAdapterQuery
import people.songpagu.goodgame.application.guild.find.incoming.GuildFindMoreUseCase
import people.songpagu.goodgame.application.guild.find.incoming.GuildNameFindUseCase

@Adapter(readOnly = true)
class GuildFindAdapter(
    private val guildFindMoreUseCase: GuildFindMoreUseCase,
    private val guildNameFindUseCase: GuildNameFindUseCase,
) {
    fun find(query: GuildFindAdapterQuery): GuildFindAdapterAnswer {
        return when (query) {
            is GuildFindAdapterQuery.More -> guildFindMoreUseCase.findMoreBy(startId = query.startId, size = query.size)
                .let { GuildFindAdapterAnswer(lastId = it.lastId, contents = it.contents) }
            is GuildFindAdapterQuery.Name -> GuildFindAdapterAnswer(
                lastId = null,
                contents = guildNameFindUseCase.findByName(guildName = query.name, size = query.size).contents,
            )
        }
    }
}
