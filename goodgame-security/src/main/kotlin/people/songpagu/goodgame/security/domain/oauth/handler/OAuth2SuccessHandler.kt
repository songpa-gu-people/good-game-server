package people.songpagu.goodgame.security.domain.oauth.handler

import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler
import org.springframework.stereotype.Component
import org.springframework.web.util.UriComponentsBuilder
import people.songpagu.goodgame.domain.member.model.LoginMember
import people.songpagu.goodgame.security.domain.oauth.cookie.GoodGameOauthCookie
import people.songpagu.goodgame.security.domain.oauth.handler.facade.OAuth2SuccessFacade
import people.songpagu.goodgame.security.domain.oauth.handler.model.GoodGameQueryString
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpSession

@Component
class OAuth2SuccessHandler(
    private val httpSession: HttpSession,
    private val oAuth2SuccessFacade: OAuth2SuccessFacade,
) : SimpleUrlAuthenticationSuccessHandler() {
    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication,
    ) {
        // refreshToken 생성
        val loginMember = httpSession.getAttribute(LoginMember.KEY) as LoginMember
        val refreshToken: String = oAuth2SuccessFacade.generateRefreshToken(loginMember)
        val authResponse = GoodGameOauthCookie(refreshToken)
            .issueRefreshCookie(response)

        // redirect 설정
        val redirectUri: String = request.session.getAttribute(GoodGameQueryString.GOOD_GAME_REDIRECT_URI) as String
        val redirectPath = UriComponentsBuilder.fromUriString(redirectUri)
            .build()
            .toUriString()

        redirectStrategy.sendRedirect(request, authResponse, redirectPath)
    }
}
