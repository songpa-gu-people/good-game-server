package people.songpagu.goodgame.jpa.domain.matching.entity

import people.songpagu.goodgame.domain.matching.option.District
import people.songpagu.goodgame.domain.member.type.Gender
import people.songpagu.goodgame.jpa.domain.base.BaseEntity
import people.songpagu.goodgame.jpa.domain.matching.converter.DistrictConverter
import people.songpagu.goodgame.jpa.domain.matching.converter.GenderConverter
import javax.persistence.*

@Entity
class MatchingOptionEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    val memberId: Long,

    @Convert(converter = DistrictConverter::class)
    @Column(nullable = false)
    val districts: List<District> = mutableListOf(),

    @Convert(converter = GenderConverter::class)
    @Column(nullable = false)
    val gender: List<Gender> = mutableListOf(),
) : BaseEntity() {
}