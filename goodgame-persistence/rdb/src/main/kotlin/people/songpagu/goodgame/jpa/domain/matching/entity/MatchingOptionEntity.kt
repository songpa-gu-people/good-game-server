package people.songpagu.goodgame.jpa.domain.matching.entity

import people.songpagu.goodgame.domain.matching.option.type.District
import people.songpagu.goodgame.domain.member.type.Gender
import people.songpagu.goodgame.jpa.domain.base.BaseEntity
import people.songpagu.goodgame.jpa.domain.matching.collection.DistrictCollection
import people.songpagu.goodgame.jpa.domain.matching.collection.GenderCollection
import people.songpagu.goodgame.jpa.domain.matching.converter.DistrictConverter
import people.songpagu.goodgame.jpa.domain.matching.converter.GenderConverter
import javax.persistence.Column
import javax.persistence.Convert
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class MatchingOptionEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(
        name = "member_number",
        nullable = false,
        unique = true,
        columnDefinition = "VARCHAR(32) COMMENT '회원번호'"
    )
    val memberNumber: String,

    @Convert(converter = DistrictConverter::class)
    @Column(name = "districts", nullable = false, columnDefinition = "VARCHAR(1024) COMMENT '지역목록'")
    val districts: DistrictCollection = DistrictCollection(),

    @Convert(converter = GenderConverter::class)
    @Column(name = "genders", nullable = false, columnDefinition = "VARCHAR(256) COMMENT '성별목록'")
    val genders: GenderCollection = GenderCollection(),
) : BaseEntity() {
    companion object {
        fun create(memberNumber: String, districts: List<District>, genders: List<Gender>): MatchingOptionEntity {
            return MatchingOptionEntity(
                memberNumber = memberNumber,
                districts = DistrictCollection(districts.toMutableList()),
                genders = GenderCollection(genders.toMutableList())
            )
        }
    }

    fun update(districts: List<District>, genders: List<Gender>) {
        this.districts.updateValues(districts)
        this.genders.updateValues(genders)
    }
}
