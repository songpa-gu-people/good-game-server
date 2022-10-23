package people.songpagu.goodgame.application.guild.find.outgoing

import people.songpagu.goodgame.application.guild.find.outgoing.dto.GuildFindQueryAnswer

interface GuildNameFindPort {
    fun findByName(guildName: String, size: Long): List<GuildFindQueryAnswer>
}
