package people.songpagu.goodgame.api.domain.guild.query.controller

import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import people.songpagu.goodgame.api.config.common.response.ApiResponse
import people.songpagu.goodgame.api.domain.guild.GuildControllerPath
import people.songpagu.goodgame.api.domain.guild.query.controller.request.GuildFindMoreRequest
import people.songpagu.goodgame.api.domain.guild.query.handler.GuildQueryHandler
import people.songpagu.goodgame.application.guild.find.incoming.GuildFindMoreUseCase.GuildFindAnswer
import people.songpagu.goodgame.security.domain.member.UserDetailsImpl

@RestController
class GuildQueryController(
    private val guildQueryHandler: GuildQueryHandler,
) {
    @GetMapping(GuildControllerPath.Query.findGuilds)
    fun findGuilds(
        @AuthenticationPrincipal member: UserDetailsImpl,
        @Validated request: GuildFindMoreRequest,
    ): ApiResponse<GuildFindAnswer> {
        val answer: GuildFindAnswer = guildQueryHandler.findMoreBy(
            startId = request.startId,
            size = request.size
        )

        return ApiResponse.Ok(answer)
    }
}
