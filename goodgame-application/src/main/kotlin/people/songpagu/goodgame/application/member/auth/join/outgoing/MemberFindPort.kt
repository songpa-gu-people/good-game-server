package people.songpagu.goodgame.application.member.auth.join.outgoing

import people.songpagu.goodgame.domain.member.model.LoginMember
import people.songpagu.goodgame.domain.member.type.LoginType

interface MemberFindPort {
    fun findByAuthIdAndLoginType(authId: String, loginType: LoginType): LoginMember?

    fun findByMemberNum(memberNumber: String): LoginMember?
}

