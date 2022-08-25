package people.songpagu.goodgame.jpa.domain.matching.entity

import people.songpagu.goodgame.domain.matching.option.type.District
import people.songpagu.goodgame.domain.member.type.Gender
import people.songpagu.goodgame.domain.member.type.LoginType
import people.songpagu.goodgame.domain.member.type.MemberStatus
import people.songpagu.goodgame.jpa.domain.base.BaseEntity
import people.songpagu.goodgame.jpa.domain.matching.converter.DistrictConverter
import people.songpagu.goodgame.jpa.domain.matching.converter.GenderConverter
import people.songpagu.goodgame.jpa.domain.member.entity.MemberEntity
import javax.persistence.*

@Entity
class MatchingOptionEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false, columnDefinition = "VARCHAR(32)")
    val memberNumber: String,

    // TODO: json으로 바꾸기
    @Convert(converter = DistrictConverter::class)
    @Column(nullable = false)
    val districts: List<District> = mutableListOf(),

    @Convert(converter = GenderConverter::class)
    @Column(nullable = false)
    val genders: List<Gender> = mutableListOf(),
) : BaseEntity() {
    companion object {
        fun create(memberNumber: String, districts: MutableList<District>, genders: MutableList<Gender>): MatchingOptionEntity {
            return MatchingOptionEntity(
                memberNumber = memberNumber,
                districts = districts,
                genders = genders
            )
        }
    }
}