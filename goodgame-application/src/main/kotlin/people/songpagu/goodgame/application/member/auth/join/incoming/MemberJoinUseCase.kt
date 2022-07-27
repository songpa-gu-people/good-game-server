package people.songpagu.goodgame.application.member.auth.join.incoming

import people.songpagu.goodgame.domain.exception.GoodGameException
import people.songpagu.goodgame.domain.member.model.LoginMember
import people.songpagu.goodgame.domain.member.type.Gender
import people.songpagu.goodgame.domain.member.type.LoginType

interface MemberJoinUseCase {
    fun join(command: MemberJoinCommand): MemberJoinAnswer

    data class MemberJoinCommand(
        val authId: String,
        val loginType: LoginType,
        val email: String?,
        val gender: Gender?,
    ) {
        val notEnoughToJoin: Boolean = email == null || gender == null
    }

    sealed interface MemberJoinAnswer {
        val loginMember: LoginMember

        data class NEW(override val loginMember: LoginMember) : MemberJoinAnswer

        data class INCOMPLETE(val message: String) : MemberJoinAnswer {
            override val loginMember: LoginMember
                get() = throw GoodGameException()
        }

        data class JOINED(override val loginMember: LoginMember) : MemberJoinAnswer
    }
}
