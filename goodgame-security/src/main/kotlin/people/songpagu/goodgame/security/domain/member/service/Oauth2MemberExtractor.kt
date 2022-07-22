package people.songpagu.goodgame.security.domain.member.service

import people.songpagu.goodgame.domain.member.type.LoginType

interface Oauth2MemberExtractor {
    fun enableExtract(registrationId: String): Boolean
    fun extract(attributes: Map<String, Any>): Oauth2ValidatedMember

    sealed class Oauth2ValidatedMember(
        val authId: String,
        val email: String,
    ) {
        abstract val type: LoginType

        class Kakao(authId: String, email: String) : Oauth2ValidatedMember(authId, email) {
            override val type: LoginType
                get() = LoginType.KAKAO
        }
    }
}
