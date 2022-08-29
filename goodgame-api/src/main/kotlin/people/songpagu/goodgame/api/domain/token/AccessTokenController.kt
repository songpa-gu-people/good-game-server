package people.songpagu.goodgame.api.domain.token

import org.springframework.web.bind.annotation.CookieValue
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import people.songpagu.goodgame.api.config.common.response.ApiResponse
import people.songpagu.goodgame.api.domain.token.handler.AccessTokenGenerateHandler
import people.songpagu.goodgame.security.domain.oauth.cookie.GoodGameOauthCookie.Companion.REFRESH_TOKEN_NAME

@RestController
class AccessTokenController(
    private val accessTokenGenerateHandler: AccessTokenGenerateHandler,
) {

    @GetMapping("/api/v1/token/access")
    fun getAccessToken(@CookieValue(REFRESH_TOKEN_NAME) refreshToken: String): ApiResponse<String> {
        val accessToken = accessTokenGenerateHandler.issue(refreshToken)
        return ApiResponse.Ok(accessToken)
    }
}
