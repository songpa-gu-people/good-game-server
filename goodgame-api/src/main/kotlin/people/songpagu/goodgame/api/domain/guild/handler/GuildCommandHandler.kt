package people.songpagu.goodgame.api.domain.guild.handler

import people.songpagu.goodgame.api.config.layer.Handler
import people.songpagu.goodgame.api.domain.guild.adaptor.GuildAdaptor
import people.songpagu.goodgame.api.domain.guild.dto.request.GuildCreateRequest

@Handler
class GuildCommandHandler(
    private val guildAdaptor: GuildAdaptor
) {
    fun createGuild(createMemberNumber: String, request: GuildCreateRequest) {
        guildAdaptor.createGuild(createMemberNumber, request)
    }
}
