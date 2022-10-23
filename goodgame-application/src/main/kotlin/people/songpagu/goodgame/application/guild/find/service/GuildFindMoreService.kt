package people.songpagu.goodgame.application.guild.find.service

import people.songpagu.goodgame.application.guild.find.incoming.GuildFindMoreUseCase
import people.songpagu.goodgame.application.guild.find.incoming.GuildFindMoreUseCase.GuildFindAnswer
import people.songpagu.goodgame.application.guild.find.incoming.GuildFindMoreUseCase.GuildFindMoreQuery
import people.songpagu.goodgame.application.guild.find.outgoing.GuildFindMorePort
import people.songpagu.goodgame.application.guild.find.outgoing.GuildFindMorePort.GuildFindMoreQueryAnswerCollection


class GuildFindMoreService(
    private val guildFindMorePort: GuildFindMorePort,
) : GuildFindMoreUseCase {
    override fun findMoreBy(query: GuildFindMoreQuery): GuildFindAnswer {
        val more: GuildFindMoreQueryAnswerCollection = guildFindMorePort.findMoreBy(
            startId = query.startId,
            size = query.size,
        )

        return GuildFindAnswer(
            contents = more.contents.map {
                GuildFindAnswer.Content(
                    guildId = it.guildId,
                    guildNumber = it.guildNumber,
                    guildMemberSize = it.guildMemberSize,
                    guildName = it.guildName,
                )
            },
            lastId = more.lastId,
        )
    }
}
