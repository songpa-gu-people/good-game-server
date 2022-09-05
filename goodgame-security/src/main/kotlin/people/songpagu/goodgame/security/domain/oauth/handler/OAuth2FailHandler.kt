package people.songpagu.goodgame.security.domain.oauth.handler

import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.DefaultRedirectStrategy
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.stereotype.Component
import people.songpagu.goodgame.security.config.properties.GoodGameRedirectUriProperty
import people.songpagu.infrastructure.log.Slf4j
import people.songpagu.infrastructure.log.Slf4j.Companion.log
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
@Slf4j
class OAuth2FailHandler(
    private val goodGameRedirectUriProperty: GoodGameRedirectUriProperty
) : AuthenticationFailureHandler {
    override fun onAuthenticationFailure(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        exception: AuthenticationException
    ) {
        log.warn("[로그인실패] message={}", exception.message)
        val redirectUrl = goodGameRedirectUriProperty.loginFail
        DefaultRedirectStrategy().sendRedirect(request, response, redirectUrl)
    }
}
