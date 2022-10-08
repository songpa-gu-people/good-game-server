package people.songpagu.goodgame.domain.guild.model

import people.songpagu.goodgame.domain.guild.collection.GuildMembers

data class Guild(
    val id: Long? = null,
    val guildNumber: String,
    val guildName: String,
    val guildMembers: GuildMembers,
)
