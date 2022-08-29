package people.songpagu.goodgame.application.matching.option.incoming

import people.songpagu.goodgame.domain.matching.option.model.MatchingOption
import people.songpagu.goodgame.domain.matching.option.type.District
import people.songpagu.goodgame.domain.member.type.Gender

interface MatchingOptionFindUseCase {
    fun findMatchingOptionBy(memberNumber: String): MatchingOptionAnswer

    data class MatchingOptionAnswer(
        val memberNumber: String,
        val districts: List<District> = emptyList(),
        val genders: List<Gender> = emptyList(),
        val exist: Boolean,
    ) {
        companion object {
            fun of(matchingOption: MatchingOption): MatchingOptionAnswer {
                return MatchingOptionAnswer(
                    memberNumber = matchingOption.memberNumber,
                    districts = matchingOption.districts,
                    genders = matchingOption.genders,
                    exist = true,
                )
            }

            fun ofEmpty(memberNumber: String): MatchingOptionAnswer {
                return MatchingOptionAnswer(
                    memberNumber = memberNumber,
                    districts = emptyList(),
                    genders = emptyList(),
                    exist = false,
                )
            }

        }
    }
}
