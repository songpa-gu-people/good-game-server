package people.songpagu.goodgame.jpa.domain.member.entity

import people.songpagu.goodgame.domain.member.type.LoginHistoryType
import people.songpagu.goodgame.jpa.domain.base.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Index
import javax.persistence.Table

@Entity
@Table(
    name = "member_login_history",
    indexes = [
        Index(name = "idx_member_login_history_1", columnList = "member_number")
    ],
)
class MemberLoginHistoryEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long? = null,

    @Column(name = "member_number", nullable = false, columnDefinition = "VARCHAR(32) COMMENT '회원번호'")
    val memberNumber: String,

    @Enumerated
    @Column(name = "status", columnDefinition = "VARCHAR(20) COMMENT '로그인 액션 타입'")
    val status: LoginHistoryType,
) : BaseEntity()
