package people.songpagu.goodgame.jpa.domain.matching.collection

import people.songpagu.goodgame.domain.member.type.Gender

data class GenderCollection(
    val values: MutableList<Gender> = mutableListOf()
) {
    fun updateValues(genders: List<Gender>) {
        this.values.clear();
        this.values.addAll(genders)
    }
}
