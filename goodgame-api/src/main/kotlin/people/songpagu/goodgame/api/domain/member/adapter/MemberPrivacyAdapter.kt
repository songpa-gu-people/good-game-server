package people.songpagu.goodgame.api.domain.member.adapter

import people.songpagu.goodgame.api.config.layer.Adapter
import people.songpagu.goodgame.application.member.privacy.incoming.MemberPrivacyFindUseCase
import people.songpagu.goodgame.application.member.privacy.incoming.MemberPrivacyFindUseCase.MemberPrivacyAnswer

@Adapter
class MemberPrivacyAdapter(
    private val memberPrivacyFindUseCase: MemberPrivacyFindUseCase
) {
    fun findMemberPrivacyBy(memberNumber: String): MemberPrivacyAnswer {
        return memberPrivacyFindUseCase.findMemberPrivacyBy(memberNumber)
    }
}