package people.songpagu.goodgame.application.matching.option.outgoing

import people.songpagu.goodgame.domain.matching.option.type.District
import people.songpagu.goodgame.domain.member.type.Gender

fun interface UpdateMatchingOptionStatePort {
    fun update(command: UpdateMatchingOptionStateCommand)

    data class UpdateMatchingOptionStateCommand(
        val memberNumber: String,
        val districts: List<District> = emptyList(),
        val genders: List<Gender> = emptyList(),
    )
}