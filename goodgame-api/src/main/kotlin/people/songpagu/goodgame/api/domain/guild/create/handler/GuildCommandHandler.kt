package people.songpagu.goodgame.api.domain.guild.create.handler

import people.songpagu.goodgame.api.config.layer.Handler
import people.songpagu.goodgame.api.domain.guild.create.adaptor.GuildAdaptor
import people.songpagu.goodgame.api.domain.guild.create.controller.request.GuildCreateRequest

@Handler
class GuildCommandHandler(
    private val guildAdaptor: GuildAdaptor
) {
    fun createGuild(createMemberNumber: String, request: GuildCreateRequest) {
        guildAdaptor.createGuild(createMemberNumber, request)
    }
}
