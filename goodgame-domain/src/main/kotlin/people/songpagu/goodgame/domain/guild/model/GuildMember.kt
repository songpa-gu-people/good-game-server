package people.songpagu.goodgame.domain.guild.model

import people.songpagu.goodgame.domain.guild.type.GuildMemberRank

data class GuildMember(
    val id: Long? = null,
    val memberNumber: String,
    val guildMemberRank: GuildMemberRank,
)