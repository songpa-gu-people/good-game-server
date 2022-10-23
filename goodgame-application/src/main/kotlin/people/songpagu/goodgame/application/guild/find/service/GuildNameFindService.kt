package people.songpagu.goodgame.application.guild.find.service

import people.songpagu.goodgame.application.guild.find.incoming.GuildNameFindUseCase
import people.songpagu.goodgame.application.guild.find.incoming.dto.GuildFindContent
import people.songpagu.goodgame.application.guild.find.outgoing.GuildNameFindPort
import people.songpagu.goodgame.application.guild.find.outgoing.dto.GuildFindQueryAnswer

class GuildNameFindService(
    private val guildNameFindPort: GuildNameFindPort,
) : GuildNameFindUseCase {
    override fun findByName(guildName: String, size: Long): GuildNameFindUseCase.GuildNameFindAnswer {
        val results: List<GuildFindQueryAnswer> = guildNameFindPort.findByName(guildName, size)

        return GuildNameFindUseCase.GuildNameFindAnswer(
            contents = results.map {
                GuildFindContent(
                    guildId = it.guildId,
                    guildNumber = it.guildNumber,
                    guildMemberSize = it.guildMemberSize,
                    guildName = it.guildName,
                )
            }
        )
    }
}
