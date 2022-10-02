package people.songpagu.goodgame.jpa.domain.matching.entity

import people.songpagu.goodgame.domain.matching.option.type.District
import people.songpagu.goodgame.domain.member.type.Gender
import people.songpagu.goodgame.jpa.domain.base.BaseEntity
import people.songpagu.goodgame.jpa.domain.matching.collection.DistrictCollection
import people.songpagu.goodgame.jpa.domain.matching.collection.GenderCollection
import people.songpagu.goodgame.jpa.domain.matching.converter.DistrictConverter
import people.songpagu.goodgame.jpa.domain.matching.converter.GenderConverter
import javax.persistence.*

@Table(
    name = "matching_option",
    uniqueConstraints = [UniqueConstraint(name = "uk_matching_option_1", columnNames = ["member_number"])],
)
@Entity
class MatchingOptionEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(
        name = "member_number",
        nullable = false,
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
        fun of(
            id: Long? = null,
            memberNumber: String,
            districts: List<District>,
            genders: List<Gender>,
        ): MatchingOptionEntity {
            return MatchingOptionEntity(
                id = id,
                memberNumber = memberNumber,
                districts = DistrictCollection(districts.toMutableList()),
                genders = GenderCollection(genders.toMutableList())
            )
        }
    }
}
