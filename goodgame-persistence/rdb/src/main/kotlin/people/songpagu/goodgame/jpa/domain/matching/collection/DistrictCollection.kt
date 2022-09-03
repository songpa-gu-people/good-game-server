package people.songpagu.goodgame.jpa.domain.matching.collection

import people.songpagu.goodgame.domain.matching.option.type.District

data class DistrictCollection(
    val values: List<District> = emptyList()
)
