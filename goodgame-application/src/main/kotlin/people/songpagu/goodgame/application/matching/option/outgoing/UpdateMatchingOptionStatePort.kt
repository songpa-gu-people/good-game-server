package people.songpagu.goodgame.application.matching.option.outgoing

import people.songpagu.goodgame.domain.matching.option.type.District
import people.songpagu.goodgame.domain.member.type.Gender

fun interface UpdateMatchingOptionStatePort {
    fun save(command: UpdateMatchingOptionStateCommand)

    data class UpdateMatchingOptionStateCommand(
        val matchingOptionEntityId: Long?,
        val memberNumber: String,
        val districts: List<District> = emptyList(),
        val genders: List<Gender> = emptyList(),
    )
}