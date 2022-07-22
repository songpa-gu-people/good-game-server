package people.songpagu.goodgame.jpa.domain.member.entity

import people.songpagu.goodgame.domain.member.type.LoginType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Index
import javax.persistence.Table
import javax.persistence.UniqueConstraint


@Entity
@Table(
    name = "member",
    indexes = [
        Index(name = "idx_member_1", columnList = "email")
    ],
    uniqueConstraints = [
        UniqueConstraint(name = "uk_member_1", columnNames = ["member_number"]),
        UniqueConstraint(name = "uk_member_2", columnNames = ["auth_id"]),
    ],
)
class MemberEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long? = null,

    @Column(name = "member_number", nullable = false, columnDefinition = "VARCHAR(32) COMMENT '회원번호'")
    val memberNumber: String,

    @Column(name = "auth_id", nullable = false, columnDefinition = "VARCHAR(128) COMMENT '회원인증 아이디'")
    val authId: String,

    @Column(name = "email", nullable = false, columnDefinition = "VARCHAR(64) COMMENT '이메일'")
    val email: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "login_type", nullable = false, columnDefinition = "VARCHAR(20) COMMENT '로그인 타입'")
    val loginType: LoginType,
)
