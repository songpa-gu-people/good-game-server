package people.songpagu.goodgame.security.domain.member.service

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Component
import people.songpagu.goodgame.domain.member.model.LoginMember
import people.songpagu.goodgame.security.domain.member.service.Oauth2MemberExtractor.Oauth2ValidatedMember
import javax.servlet.http.HttpSession

@Component
class WebSecurityUserService(
    private val extractorGroup: List<Oauth2MemberExtractor>,
    private val securityMemberService: SecurityMemberService,
    private val httpSession: HttpSession,
) : DefaultOAuth2UserService() {

    override fun loadUser(userRequest: OAuth2UserRequest): OAuth2User {
        val oauth2User: OAuth2User = super.loadUser(userRequest)
        val extractor: Oauth2MemberExtractor = extractorGroup.first { it.enableExtract(userRequest.clientRegistration.registrationId) }
        val oauth2Member: Oauth2ValidatedMember = extractor.extract(oauth2User.attributes)

        val loginMember: LoginMember = securityMemberService.signUpOrIn(oauth2Member)
        httpSession.setAttribute(LoginMember.KEY, loginMember)
        return oauth2User
    }
}
