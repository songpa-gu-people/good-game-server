package people.songpagu.goodgame.api.domain.member.privacy.dto.response

import people.songpagu.goodgame.application.member.privacy.incoming.MemberPrivacyFindUseCase
import people.songpagu.goodgame.domain.member.type.Gender
import people.songpagu.goodgame.domain.member.type.MemberPrivacyStatus
import javax.validation.constraints.NotNull

data class MemberPrivacyResponse(
    @field:NotNull var memberNumber: String? = null,
    @field:NotNull var email: String? = null,
    @field:NotNull var gender: Gender? = null,
    @field:NotNull var memberPrivacyStatus: MemberPrivacyStatus? = null
) {
    constructor(answer: MemberPrivacyFindUseCase.MemberPrivacyAnswer) : this(
        memberNumber = answer.memberNumber,
        email = answer.email,
        gender = answer.gender,
        memberPrivacyStatus = answer.memberPrivacyStatus
    )
}