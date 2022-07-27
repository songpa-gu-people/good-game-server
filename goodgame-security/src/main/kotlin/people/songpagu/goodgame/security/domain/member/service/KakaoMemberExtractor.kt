package people.songpagu.goodgame.security.domain.member.service

import org.springframework.stereotype.Component
import people.songpagu.goodgame.domain.member.type.LoginType
import people.songpagu.goodgame.security.domain.member.service.Oauth2MemberExtractor.Oauth2ValidatedMember

@Component
class KakaoMemberExtractor : Oauth2MemberExtractor {

    override fun enableExtract(registrationId: String): Boolean {
        return LoginType.KAKAO.registrationId == registrationId
    }

    override fun extract(attributes: Map<String, Any>): Oauth2ValidatedMember {
        val id = attributes["id"] as Long
        val kakaoAccount = attributes["kakao_account"] as Map<*, *>

        return Oauth2ValidatedMember.Kakao(
            authId = id.toString(),
            email = kakaoAccount["email"] as String?,
            gender = kakaoAccount["gender"] as String?,
        )
    }
}
