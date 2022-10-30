package people.songpagu.goodgame.api.domain.guild.create.adaptor

import people.songpagu.goodgame.api.config.layer.Adapter
import people.songpagu.goodgame.api.domain.guild.create.controller.request.GuildCreateRequest
import people.songpagu.goodgame.application.guild.create.incoming.GuildCreateUseCase

@Adapter
class GuildAdaptor(
    private val guildCreateUseCase: GuildCreateUseCase
) {
    fun createGuild(createMemberNumber: String, request: GuildCreateRequest) {
        val guildCreateCommand = GuildCreateUseCase.GuildCreateCommand(
            createMemberNumber = createMemberNumber,
            guildName = request.guildName
        )
        guildCreateUseCase.createGuild(guildCreateCommand)
    }
}
