package people.songpagu.goodgame.application.guild.incoming

interface GuildCreateUseCase {
    fun createGuild(guildCreateCommand: GuildCreateCommand)

    data class GuildCreateCommand(
        val createMemberNumber: String,
        val guildName: String,
    )
}