package people.songpagu.goodgame.api.domain.token

import org.springframework.http.HttpStatus
import org.springframework.web.bind.MissingRequestCookieException
import org.springframework.web.bind.annotation.*
import people.songpagu.goodgame.api.config.common.response.ApiResponse
import people.songpagu.goodgame.api.domain.token.handler.AccessTokenGenerateHandler
import people.songpagu.goodgame.domain.exception.GoodGameCode
import people.songpagu.goodgame.security.domain.oauth.cookie.GoodGameOauthCookie.Companion.REFRESH_TOKEN_NAME

@RestController
class AccessTokenController(
    private val accessTokenGenerateHandler: AccessTokenGenerateHandler,
) {

    @GetMapping(AccessTokenControllerPath.getAccessToken)
    fun getAccessToken(@CookieValue(REFRESH_TOKEN_NAME) refreshToken: String): ApiResponse<String> {
        val accessToken = accessTokenGenerateHandler.issue(refreshToken)
        return ApiResponse.Ok(accessToken)
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingRequestCookieException::class)
    fun handleException(e: MissingRequestCookieException): ApiResponse<Void> {
        return ApiResponse.Fail(
            code = GoodGameCode.ACCESS_TOKEN_FAIL,
        )
    }
}
