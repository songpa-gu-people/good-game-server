package people.songpagu.goodgame.application.matching.option.incoming

import people.songpagu.goodgame.domain.matching.option.model.MatchingOption
import people.songpagu.goodgame.domain.matching.option.type.District
import people.songpagu.goodgame.domain.member.type.Gender
import people.songpagu.goodgame.domain.member.type.LoginType
import people.songpagu.goodgame.domain.member.type.MemberStatus
import java.util.StringJoiner

interface MatchingOptionFindUseCase {
    fun findMatchingOptionBy(memberNumber: String): MatchingOptionResponse

    data class MatchingOptionResponse(
        var memberNumber: String? = null,
        var districts: List<District>? = null,
        var genders: List<Gender>? = null,
        var exist: Boolean? = null
    ) {
        companion object {
            fun of(matchingOption: MatchingOption): MatchingOptionResponse {
                return MatchingOptionResponse(
                    matchingOption.memberNumber,
                    matchingOption.districts,
                    matchingOption.genders,
                    true
                )
            }

            fun ofEmpty(): MatchingOptionResponse {
                return MatchingOptionResponse(null, null, null, false)
            }

        }
    }
}