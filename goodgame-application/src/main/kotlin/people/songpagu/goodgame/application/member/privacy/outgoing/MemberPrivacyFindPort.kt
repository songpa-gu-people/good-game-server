package people.songpagu.goodgame.application.member.privacy.outgoing

import people.songpagu.goodgame.domain.member.model.MemberPrivacy

interface MemberPrivacyFindPort {
    fun findBy(memberNumber: String): MemberPrivacy?
}