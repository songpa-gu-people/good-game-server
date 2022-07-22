package people.songpagu.goodgame.security.domain.oauth.handler.request

import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository
import org.springframework.security.oauth2.client.web.HttpSessionOAuth2AuthorizationRequestRepository
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest
import org.springframework.stereotype.Component
import people.songpagu.goodgame.security.domain.oauth.handler.model.GoodGameQueryString
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class GoodGameOAuth2AuthorizationRepository(
    private val delegate: AuthorizationRequestRepository<OAuth2AuthorizationRequest> = HttpSessionOAuth2AuthorizationRequestRepository(),
) : AuthorizationRequestRepository<OAuth2AuthorizationRequest> by delegate {

    override fun removeAuthorizationRequest(request: HttpServletRequest, response: HttpServletResponse?): OAuth2AuthorizationRequest {
        if (request.session.getAttribute(DEFAULT_AUTHORIZATION_REQUEST_ATTR_NAME) == null) {
            request.session.removeAttribute(GoodGameQueryString.GOOD_GAME_REDIRECT_URI)
        }

        return super.removeAuthorizationRequest(request, response)
    }

    override fun saveAuthorizationRequest(
        authorizationRequest: OAuth2AuthorizationRequest?,
        request: HttpServletRequest,
        response: HttpServletResponse,
    ) {
        val redirectUri: String? = GoodGameQueryString(request.queryString).get(GoodGameQueryString.GOOD_GAME_REDIRECT_URI).firstOrNull()

        // 나중에 인가된 redirect_uri 만 허용하기
        redirectUri?.run {
            request.session.setAttribute(GoodGameQueryString.GOOD_GAME_REDIRECT_URI, this)
        }

        delegate.saveAuthorizationRequest(authorizationRequest, request, response)
    }

    companion object {
        private val DEFAULT_AUTHORIZATION_REQUEST_ATTR_NAME: String =
            HttpSessionOAuth2AuthorizationRequestRepository::class.java.name + ".AUTHORIZATION_REQUEST"
    }
}
