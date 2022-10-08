package people.songpagu.goodgame.jpa.domain.guild.entity

import java.io.Serializable

data class GuildMemberEntityId(
    var memberNumber: String? = null,
    var guildEntity: Long? = null,
) : Serializable