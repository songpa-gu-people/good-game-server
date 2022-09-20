package people.songpagu.goodgame.jpa.domain.member.entity

import people.songpagu.goodgame.domain.member.type.Gender
import people.songpagu.goodgame.domain.member.type.MemberPrivacyStatus
import people.songpagu.goodgame.jpa.domain.base.BaseEntity
import javax.persistence.*

@Entity
@Table(
    name = "member_privacy",
    indexes = [
        Index(name = "idx_member_1", columnList = "email"),
        Index(name = "idx_member_2", columnList = "gender"),
    ],
    uniqueConstraints = [
        UniqueConstraint(name = "uk_member_privacy_1", columnNames = ["member_number"]),
    ],
)
class MemberPrivacyEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long? = null,

    @Column(name = "member_number", nullable = false, columnDefinition = "VARCHAR(32) COMMENT '회원번호'")
    val memberNumber: String,

    _status: MemberPrivacyStatus,
    _email: String,
    _gender: Gender,
) : BaseEntity() {
    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "VARCHAR(20) COMMENT '개인정보 수집상태'")
    var status: MemberPrivacyStatus = _status
        private set

    @Column(name = "email", columnDefinition = "VARCHAR(64) COMMENT '이메일'")
    var email: String = _email
        private set

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", columnDefinition = "VARCHAR(20) COMMENT '성별'")
    var gender: Gender = _gender
        private set

    companion object {
        fun create(
            memberEntity: MemberEntity,
            email: String,
            gender: Gender,
        ): MemberPrivacyEntity {
            return MemberPrivacyEntity(
                memberNumber = memberEntity.memberNumber,
                _status = MemberPrivacyStatus.IN_PROGRESS,
                _email = email,
                _gender = gender
            )
        }
    }
}
