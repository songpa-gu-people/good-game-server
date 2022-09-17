package people.songpagu.goodgame.api.domain.member.handler

import people.songpagu.goodgame.api.config.layer.Handler
import people.songpagu.goodgame.api.domain.member.adapter.MemberPrivacyAdapter
import people.songpagu.goodgame.application.member.privacy.incoming.MemberPrivacyFindUseCase.MemberPrivacyAnswer

@Handler
class MemberPrivacyHandler(
    private val memberPrivacyAdapter: MemberPrivacyAdapter
) {
    fun findMemberPrivacyBy(memberNumber: String): MemberPrivacyAnswer {
        return memberPrivacyAdapter.findMemberPrivacyBy(memberNumber);
    }
}