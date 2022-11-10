package people.songpagu.goodgame.application.guild.find.service

import people.songpagu.goodgame.application.guild.find.incoming.GuildFindMoreUseCase
import people.songpagu.goodgame.application.guild.find.incoming.GuildFindMoreUseCase.GuildFindAnswer
import people.songpagu.goodgame.application.guild.find.incoming.dto.GuildFindContent
import people.songpagu.goodgame.application.guild.find.outgoing.GuildListFindPort
import people.songpagu.goodgame.application.guild.find.outgoing.GuildListFindPort.GuildFindMoreQueryAnswerCollection


class GuildFindMoreService(
    private val guildListFindPort: GuildListFindPort,
) : GuildFindMoreUseCase {
    override fun findMoreBy(
        name: String?,
        pageNumber: Int,
        pageSize: Int,
    ): GuildFindAnswer {
        val more: GuildFindMoreQueryAnswerCollection = guildListFindPort.findMoreBy(
            condition = GuildListFindPort.GuildFindQueryCondition(name = name),
            pageNumber = pageNumber,
            pageSize = pageSize
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
            totalPageSize = more.totalPageSize
        )
    }
}
