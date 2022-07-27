package people.songpagu.goodgame.application.member.auth.join.outgoing

import people.songpagu.goodgame.domain.member.model.LoginMember
import people.songpagu.goodgame.domain.member.type.Gender
import people.songpagu.goodgame.domain.member.type.LoginType

fun interface MemberCreatePort {
    fun create(command: MemberCreateCommand): LoginMember

    data class MemberCreateCommand(
        val memberNumber: String,
        val authId: String,
        val loginType: LoginType,
        val email: String,
        val gender: Gender,
    )
}

