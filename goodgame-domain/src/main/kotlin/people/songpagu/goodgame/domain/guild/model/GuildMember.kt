package people.songpagu.goodgame.domain.guild.model

import people.songpagu.goodgame.domain.guild.type.GuildMemberPosition

data class GuildMember(
    val id: Long? = null,
    val memberNumber: String,
    val guildMemberPosition: GuildMemberPosition,
)