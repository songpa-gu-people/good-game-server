package people.songpagu.goodgame.api.domain.guild.dto.response

data class GuildFindPageResponse(
    val guildId: Long,
    val guildNumber: String,
    val guildMemberSize: Int,
    val guildName: String,
)
