package people.songpagu.goodgame.api.domain.guild.query.handler

import people.songpagu.goodgame.api.config.layer.Handler
import people.songpagu.goodgame.application.guild.find.incoming.GuildFindMoreUseCase

@Handler
class GuildQueryHandler(
    private val guildFindMoreUseCase: GuildFindMoreUseCase,
) {
    fun findMoreBy(startId: Long?, size: Long): GuildFindMoreUseCase.GuildFindAnswer {
        return guildFindMoreUseCase.findMoreBy(
            GuildFindMoreUseCase.GuildFindMoreQuery(
                startId = startId, size = size
            )
        )
    }
}
