package people.songpagu.goodgame.application.guild.find.service

import people.songpagu.goodgame.application.guild.find.incoming.GuildFindMoreUseCase
import people.songpagu.goodgame.application.guild.find.incoming.GuildFindMoreUseCase.GuildFindAnswer
import people.songpagu.goodgame.application.guild.find.incoming.dto.GuildFindContent
import people.songpagu.goodgame.application.guild.find.outgoing.GuildFindMorePort
import people.songpagu.goodgame.application.guild.find.outgoing.GuildFindMorePort.GuildFindMoreQueryAnswerCollection


class GuildFindMoreService(
    private val guildFindMorePort: GuildFindMorePort,
) : GuildFindMoreUseCase {
    override fun findMoreBy(startId: Long?, size: Long): GuildFindAnswer {
        val more: GuildFindMoreQueryAnswerCollection = guildFindMorePort.findMoreBy(
            startId = startId,
            size = size,
        )

        return GuildFindAnswer(
            contents = more.contents.map {
                GuildFindContent(
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
