package people.songpagu.goodgame.api.domain.matching.option.dto.request

import people.songpagu.goodgame.domain.matching.option.type.District
import people.songpagu.goodgame.domain.member.type.Gender
import javax.validation.constraints.NotEmpty

data class MatchingOptionSaveOrUpdateRequest(
    @field: NotEmpty(message = "상대를 매칭할 '장소'를 선택해주세요.")
    val districts: List<District>,
    @field: NotEmpty(message = "상대를 매칭할 '성별'을 선택해주세요.")
    val genders: List<Gender>
)