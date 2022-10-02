package people.songpagu.goodgame.domain.guild.model

data class Guild(
    val id: Long? = null,
    val guildNumber: String,
    val guildName: String,
    val guildMembers: List<GuildMember>,
)
