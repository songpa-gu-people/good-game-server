package people.songpagu.goodgame.security.domain.member.service

import org.springframework.stereotype.Component
import people.songpagu.goodgame.domain.member.type.LoginType
import people.songpagu.goodgame.security.domain.member.service.Oauth2MemberExtractor.*

@Component
class KakaoMemberExtractor : Oauth2MemberExtractor {

    override fun enableExtract(registrationId: String): Boolean {
        return LoginType.KAKAO.registrationId == registrationId
    }

    override fun extract(attributes: Map<String, Any>): Oauth2ValidatedMember {
        val id = attributes["id"] as Long
        val kakaoAccount = attributes["kakao_account"] as Map<*, *>

        return Oauth2ValidatedMember.Kakao(id.toString(), kakaoAccount["email"] as String)
    }
}
