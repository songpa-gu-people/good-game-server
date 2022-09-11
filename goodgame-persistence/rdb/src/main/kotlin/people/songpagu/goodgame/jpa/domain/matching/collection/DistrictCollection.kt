package people.songpagu.goodgame.jpa.domain.matching.collection

import people.songpagu.goodgame.domain.matching.option.type.District

data class DistrictCollection(
    var values: List<District> = listOf()
) {
    fun updateValues(districts: List<District>) {
        this.values = districts
    }
}