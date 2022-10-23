package people.songpagu.goodgame.application.guild.find.incoming.dto

data class GuildFindContent(
    val guildId: Long,
    val guildNumber: String,
    val guildMemberSize: Long,
    val guildName: String,
)
