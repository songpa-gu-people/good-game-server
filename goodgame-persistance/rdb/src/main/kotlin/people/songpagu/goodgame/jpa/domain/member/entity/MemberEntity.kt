package people.songpagu.goodgame.jpa.domain.member.entity

import people.songpagu.goodgame.domain.member.type.LoginType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.UniqueConstraint


@Entity
@Table(
    name = "member",
    uniqueConstraints = [
        UniqueConstraint(name = "uk_member", columnNames = ["member_number"])
    ],
)
class MemberEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long? = null,

    @Column(name = "member_number", nullable = false, columnDefinition = "VARCHAR(32) COMMENT '회원번호'")
    val memberNumber: String,

    @Column(name = "member_details", nullable = false, columnDefinition = "VARCHAR(2048) COMMENT '회원상세'")
    val memberDetails: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "login_type", nullable = false, columnDefinition = "VARCHAR(20) COMMENT '로그인 타입'")
    val loginType: LoginType,
)
