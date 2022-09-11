package people.songpagu.goodgame.domain.matching.option.model

import people.songpagu.goodgame.domain.matching.option.type.District
import people.songpagu.goodgame.domain.member.type.Gender

data class MatchingOption(
    val id: Long? = null,
    val memberNumber: String,
    val districts: List<District>,
    val genders: List<Gender>
)
