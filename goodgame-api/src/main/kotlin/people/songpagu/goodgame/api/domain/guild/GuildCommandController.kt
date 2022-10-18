package people.songpagu.goodgame.api.domain.guild

import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import people.songpagu.goodgame.api.config.common.response.ApiResponse
import people.songpagu.goodgame.api.domain.guild.dto.request.GuildCreateRequest
import people.songpagu.goodgame.api.domain.guild.handler.GuildCommandHandler
import people.songpagu.goodgame.security.domain.member.UserDetailsImpl

@RestController
class GuildCommandController(
    private val guildCommandHandler: GuildCommandHandler
) {

    @PostMapping(GuildControllerPath.Command.createGuild)
    fun createGuild(
        @AuthenticationPrincipal member: UserDetailsImpl,
        @Validated @RequestBody guildCreateRequest: GuildCreateRequest,
    ): ApiResponse<Void> {
        guildCommandHandler.createGuild(member.memberNumber, guildCreateRequest)
        return ApiResponse.Ok()
    }
}
