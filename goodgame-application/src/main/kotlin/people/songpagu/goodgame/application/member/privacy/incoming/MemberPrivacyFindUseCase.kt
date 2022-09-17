package people.songpagu.goodgame.application.member.privacy.incoming

import people.songpagu.goodgame.domain.member.type.Gender
import people.songpagu.goodgame.domain.member.type.MemberPrivacyStatus
import javax.swing.text.html.parser.Entity

interface MemberPrivacyFindUseCase {
    fun findMemberPrivacyBy(memberNumber: String): MemberPrivacyAnswer

    data class MemberPrivacyAnswer(
        val memberNumber: String,
        val email: String,
        val gender: Gender,
        val memberPrivacyStatus: MemberPrivacyStatus
    )
}