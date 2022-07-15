package people.songpagu.goodgame.security.domain.member.service

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Component
import javax.servlet.http.HttpSession

@Component
class WebSecurityUserService(
    private val extractorGroup: List<Oauth2MemberExtractor>,
    private val securityMemberService: SecurityMemberService,
    private val httpSession: HttpSession,
) : DefaultOAuth2UserService() {

    override fun loadUser(userRequest: OAuth2UserRequest): OAuth2User {
        val oauth2User = super.loadUser(userRequest)
        val extractor = extractorGroup.first { it.enableExtract(userRequest.clientRegistration.registrationId) }
        val oauth2Member = extractor.extract(oauth2User.attributes)

        //jwt 생성

        val loginMember = securityMemberService.signUpOrIn(oauth2Member.email)
        httpSession.setAttribute("user", loginMember)
        return oauth2User
    }
}
