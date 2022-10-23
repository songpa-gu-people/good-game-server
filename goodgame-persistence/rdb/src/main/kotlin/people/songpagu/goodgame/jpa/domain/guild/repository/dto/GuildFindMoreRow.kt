package people.songpagu.goodgame.jpa.domain.guild.repository.dto

import people.songpagu.goodgame.application.guild.find.outgoing.GuildFindMorePort

data class GuildFindMoreRow(
    val _guildId: Long? = null,
    val _guildNumber: String? = null,
    val _guildMemberSize: Long? = null,
    val _guildName: String? = null,
) {
    val guildFindMoreQueryAnswer: GuildFindMorePort.GuildFindMoreQueryAnswer
        get() = GuildFindMorePort.GuildFindMoreQueryAnswer(
            guildId = _guildId!!,
            guildNumber = _guildNumber!!,
            guildMemberSize = _guildMemberSize!!,
            guildName = _guildName!!,
        )
}
