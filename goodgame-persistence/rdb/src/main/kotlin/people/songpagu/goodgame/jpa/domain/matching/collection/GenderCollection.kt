package people.songpagu.goodgame.jpa.domain.matching.collection

import people.songpagu.goodgame.domain.member.type.Gender

data class GenderCollection(
    val values: List<Gender> = emptyList()
)
