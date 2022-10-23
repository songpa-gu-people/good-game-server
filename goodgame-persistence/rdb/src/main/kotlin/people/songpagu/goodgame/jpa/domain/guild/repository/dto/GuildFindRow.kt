package people.songpagu.goodgame.jpa.domain.guild.repository.dto

import people.songpagu.goodgame.application.guild.find.outgoing.dto.GuildFindQueryAnswer

data class GuildFindRow(
    val _guildId: Long? = null,
    val _guildNumber: String? = null,
    val _guildMemberSize: Long? = null,
    val _guildName: String? = null,
) {
    val guildFindQueryAnswer: GuildFindQueryAnswer
        get() = GuildFindQueryAnswer(
            guildId = _guildId!!,
            guildNumber = _guildNumber!!,
            guildMemberSize = _guildMemberSize!!,
            guildName = _guildName!!,
        )
}
