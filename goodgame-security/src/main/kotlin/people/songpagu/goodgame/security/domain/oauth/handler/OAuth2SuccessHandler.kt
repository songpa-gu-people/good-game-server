package people.songpagu.goodgame.security.domain.oauth.handler

import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler
import org.springframework.stereotype.Component
import people.songpagu.goodgame.security.domain.jwt.service.JwtTokenGenerateService
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class OAuth2SuccessHandler(
    val jwtTokenGenerateService: JwtTokenGenerateService,
) : SimpleUrlAuthenticationSuccessHandler() {
    override fun onAuthenticationSuccess(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        chain: FilterChain?,
        authentication: Authentication?,
    ) {
        TODO("JWT 토큰 발행")
        super.onAuthenticationSuccess(request, response, chain, authentication)
    }
}
