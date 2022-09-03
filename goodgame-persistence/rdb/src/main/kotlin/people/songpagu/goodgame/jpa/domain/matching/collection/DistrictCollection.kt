package people.songpagu.goodgame.jpa.domain.matching.collection

import people.songpagu.goodgame.domain.matching.option.type.District

data class DistrictCollection(
    val values: MutableList<District> = mutableListOf()
) {
    fun updateValues(districts: List<District>) {
        this.values.clear();
        this.values.addAll(districts)
    }
}