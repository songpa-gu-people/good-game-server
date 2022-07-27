package people.songpagu.goodgame.api.config.interceptor

import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import people.songpagu.goodgame.application.token.decode.incoming.TokenAuthenticateUseCase
import java.time.LocalDateTime
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthenticationInterceptor(
    private val tokenAuthenticateUseCase: TokenAuthenticateUseCase,
) : HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val authorization: String = request.getHeader(HttpHeaders.AUTHORIZATION) // Authorization Bearer asdfasdf-asdf-asdf

        val accessToken: String? = authorization.split("Bearer ").firstOrNull()
        requireNotNull(accessToken) { "인증 토큰이 없습니다." }

        tokenAuthenticateUseCase.validate(token = accessToken, validateTime = LocalDateTime.now())

        return super.preHandle(request, response, handler)
    }
}
