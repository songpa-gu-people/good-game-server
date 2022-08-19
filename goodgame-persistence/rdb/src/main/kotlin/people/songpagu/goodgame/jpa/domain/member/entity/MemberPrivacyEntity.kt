package people.songpagu.goodgame.jpa.domain.member.entity

import people.songpagu.goodgame.domain.member.type.Gender
import people.songpagu.goodgame.domain.member.type.MemberPrivacyStatus
import people.songpagu.goodgame.jpa.domain.base.BaseEntity
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Index
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(
    name = "member_privacy",
    indexes = [
        Index(name = "idx_member_1", columnList = "email"),
        Index(name = "idx_member_2", columnList = "gender"),
    ],
)
class MemberPrivacyEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long? = null,

    @ManyToOne(cascade = [CascadeType.PERSIST])
    @JoinColumn(name = "member_id")
    val memberEntity: MemberEntity,

    _status: MemberPrivacyStatus,
    _email: String,
    _gender: Gender,
) : BaseEntity() {
    @Enumerated
    @Column(name = "status", columnDefinition = "VARCHAR(20) COMMENT '개인정보 수집상태'")
    var status: MemberPrivacyStatus = _status
        private set

    @Column(name = "email", columnDefinition = "VARCHAR(64) COMMENT '이메일'")
    var email: String = _email
        private set

    @Enumerated
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
                memberEntity = memberEntity,
                _status = MemberPrivacyStatus.IN_PROGRESS,
                _email = email,
                _gender = gender
            )
        }
    }
}
