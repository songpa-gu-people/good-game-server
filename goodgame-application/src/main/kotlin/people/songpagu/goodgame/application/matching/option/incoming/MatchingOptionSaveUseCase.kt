package people.songpagu.goodgame.application.matching.option.incoming

import people.songpagu.goodgame.domain.matching.option.type.District
import people.songpagu.goodgame.domain.member.type.Gender

interface MatchingOptionSaveUseCase {
    fun saveOrUpdate(memberNumber: String, command: MatchingOptionSaveCommand)

    data class MatchingOptionSaveCommand(
        val districts: List<District> = emptyList(),
        val genders: List<Gender> = emptyList(),
    )
}
