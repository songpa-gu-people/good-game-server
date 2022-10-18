package people.songpagu.goodgame.api.domain.guild

import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import people.songpagu.goodgame.api.config.common.response.ApiResponse
import people.songpagu.goodgame.api.domain.guild.dto.request.GuildFindPageRequest
import people.songpagu.goodgame.api.domain.guild.dto.response.GuildFindPageResponse
import people.songpagu.goodgame.common.page.PageData
import people.songpagu.goodgame.security.domain.member.UserDetailsImpl

@RestController
class GuildQueryController {
    @GetMapping(GuildControllerPath.Query.findGuilds)
    fun findGuilds(
        @AuthenticationPrincipal member: UserDetailsImpl,
        @Validated @RequestBody request: GuildFindPageRequest,
    ): ApiResponse<PageData<GuildFindPageResponse>> {

        return ApiResponse.Ok()
    }
}
