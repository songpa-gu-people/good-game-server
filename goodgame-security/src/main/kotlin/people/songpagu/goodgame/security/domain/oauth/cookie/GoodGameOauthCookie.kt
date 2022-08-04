package people.songpagu.goodgame.security.domain.oauth.cookie

import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletResponse

class GoodGameOauthCookie(
    private val token: String,
) {
    fun issueRefreshCookie(response: HttpServletResponse): HttpServletResponse {
        val cookie = Cookie(REFRESH_TOKEN_NAME, token)
        cookie.path = "/"
        cookie.isHttpOnly = true
        cookie.secure = true
        response.addCookie(cookie)
        return response
    }

    fun invalidateRefreshCookie(response: HttpServletResponse):HttpServletResponse{
        val cookie = Cookie(REFRESH_TOKEN_NAME, null)
        cookie.path = "/"
        cookie.isHttpOnly = true
        cookie.secure = true
        cookie.maxAge = 0
        response.addCookie(cookie)
        return response
    }

    companion object {
        const val REFRESH_TOKEN_NAME: String = "Gg_R"
    }
}
