package people.songpagu.goodgame.application.guild.find.outgoing.dto

data class GuildFindQueryAnswer(
        val guildId: Long,
        val guildNumber: String,
        val guildMemberSize: Long,
        val guildName: String,
    )
