package people.songpagu.goodgame.jpa.domain.matching.collection

import people.songpagu.goodgame.domain.member.type.Gender

data class GenderCollection(
    var values: List<Gender> = listOf()
) {
    fun updateValues(genders: List<Gender>) {
        this.values = genders
    }
}
