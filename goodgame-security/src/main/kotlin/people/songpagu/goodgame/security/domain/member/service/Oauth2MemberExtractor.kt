package people.songpagu.goodgame.security.domain.member.service

import people.songpagu.goodgame.application.member.auth.join.incoming.MemberJoinUseCase
import people.songpagu.goodgame.domain.member.type.Gender
import people.songpagu.goodgame.domain.member.type.LoginType

interface Oauth2MemberExtractor {
    fun enableExtract(registrationId: String): Boolean
    fun extract(attributes: Map<String, Any>): Oauth2ValidatedMember

    sealed class Oauth2ValidatedMember(
        val authId: String,
        val email: String?,
        val gender: String?,
    ) {
        abstract val loginType: LoginType

        abstract fun toMemberJoinCommand(): MemberJoinUseCase.MemberJoinCommand

        class Kakao(
            authId: String,
            email: String?,
            gender: String?,
        ) : Oauth2ValidatedMember(
            authId = authId,
            email = email,
            gender = gender
        ) {
            override val loginType: LoginType
                get() = LoginType.KAKAO

            override fun toMemberJoinCommand(): MemberJoinUseCase.MemberJoinCommand {
                return MemberJoinUseCase.MemberJoinCommand(
                    authId = authId,
                    loginType = loginType,
                    email = email,
                    gender = KakaoGender.findBy(gender)?.goodGameGender,
                )
            }

            enum class KakaoGender(val value: String, val goodGameGender: Gender) {
                MAN("male", Gender.MAN),
                WOMAN("female", Gender.WOMAN),
                ;

                companion object {
                    fun findBy(value: String?): KakaoGender? = values().find { it.value == value }
                }
            }
        }
    }
}
