package people.songpagu.goodgame.application.member.privacy.service

import people.songpagu.goodgame.application.member.privacy.incoming.MemberPrivacyFindUseCase
import people.songpagu.goodgame.application.member.privacy.incoming.MemberPrivacyFindUseCase.MemberPrivacyAnswer
import people.songpagu.goodgame.application.member.privacy.outgoing.MemberPrivacyFindPort
import people.songpagu.goodgame.domain.member.model.MemberPrivacy

class MemberPrivacyFindService(
    private val memberPrivacyFindPort: MemberPrivacyFindPort
) : MemberPrivacyFindUseCase {
    override fun findMemberPrivacyBy(memberNumber: String): MemberPrivacyAnswer {
        val memberPrivacy: MemberPrivacy = memberPrivacyFindPort.findBy(memberNumber)
        return MemberPrivacyAnswer(
            memberNumber = memberPrivacy.memberNumber,
            email = memberPrivacy.email,
            gender = memberPrivacy.gender,
            memberPrivacyStatus = memberPrivacy.memberPrivacyStatus
        )
    }
}