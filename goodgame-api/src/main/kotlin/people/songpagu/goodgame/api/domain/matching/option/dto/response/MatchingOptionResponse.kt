package people.songpagu.goodgame.api.domain.matching.option.dto.response

import people.songpagu.goodgame.application.matching.option.incoming.MatchingOptionFindUseCase
import people.songpagu.goodgame.domain.matching.option.type.District
import people.songpagu.goodgame.domain.member.type.Gender
import javax.validation.constraints.NotNull

data class MatchingOptionResponse(
    @field:NotNull var memberNumber: String? = null,
    val districts: List<District> = emptyList(),
    val genders: List<Gender> = emptyList(),
    @field:NotNull var exist: Boolean? = null,
) {
    constructor(answer: MatchingOptionFindUseCase.MatchingOptionAnswer) : this(
        memberNumber = answer.memberNumber,
        districts = answer.districts,
        genders = answer.genders,
        exist = answer.exist,
    )
}
