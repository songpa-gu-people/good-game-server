package people.songpagu.goodgame.api.domain.token

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import people.songpagu.goodgame.api.domain.response.ApiResponse
import people.songpagu.goodgame.api.domain.token.facade.AccessTokenGenerateFacade

@RestController
class AccessTokenController(
    private val accessTokenGenerateFacade: AccessTokenGenerateFacade,
) {

    @GetMapping("/api/v1/token/access")
    fun getAccessToken(@RequestParam refreshToken: String): ApiResponse<String> {
        val accessToken = accessTokenGenerateFacade.issue(refreshToken)

        return ApiResponse.Ok(accessToken)
    }
}
