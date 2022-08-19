package people.songpagu.goodgame.security.domain.oauth.handler

import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.DefaultRedirectStrategy
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.stereotype.Component
import people.songpagu.goodgame.security.config.properties.GoodGameRedirectUriProperty
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class OAuth2FailHandler(
    private val goodGameRedirectUriProperty: GoodGameRedirectUriProperty
) : AuthenticationFailureHandler {
    override fun onAuthenticationFailure(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        exception: AuthenticationException?
    ) {
        val redirectUrl = goodGameRedirectUriProperty.loginFail
        DefaultRedirectStrategy().sendRedirect(request, response, redirectUrl)
    }
}