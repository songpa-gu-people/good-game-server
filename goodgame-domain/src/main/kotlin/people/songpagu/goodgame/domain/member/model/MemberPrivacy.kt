package people.songpagu.goodgame.domain.member.model

import people.songpagu.goodgame.domain.member.type.Gender
import people.songpagu.goodgame.domain.member.type.MemberPrivacyStatus

data class MemberPrivacy(
    val memberNumber: String,
    val email: String,
    val gender: Gender,
    val memberPrivacyStatus: MemberPrivacyStatus,
)