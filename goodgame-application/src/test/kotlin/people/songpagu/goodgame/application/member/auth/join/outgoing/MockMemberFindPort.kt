package people.songpagu.goodgame.application.member.auth.join.outgoing

import people.songpagu.goodgame.domain.member.model.LoginMember
import people.songpagu.goodgame.domain.member.type.LoginType

open class MockMemberFindPort : MemberFindPort {
    override fun findByAuthIdAndLoginType(authId: String, loginType: LoginType): LoginMember? {
        throw UnsupportedOperationException()
    }

    override fun findByMemberNum(memberNumber: String): LoginMember? {
        throw UnsupportedOperationException()
    }
}
