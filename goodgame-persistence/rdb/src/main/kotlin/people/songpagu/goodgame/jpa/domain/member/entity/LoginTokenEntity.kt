package people.songpagu.goodgame.jpa.domain.member.entity

import people.songpagu.goodgame.domain.token.type.LoginTokenType
import people.songpagu.goodgame.jpa.domain.base.BaseEntity
import java.time.LocalDateTime
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
    name = "login_token",
    uniqueConstraints = [
        UniqueConstraint(name = "uk_login_token_1", columnNames = ["subject", "member_number"]),
    ],
)
class LoginTokenEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "token_type", nullable = false, columnDefinition = "VARCHAR(20) COMMENT '토큰 타입'")
    val tokenType: LoginTokenType,

    @Column(name = "subject", nullable = false, columnDefinition = "VARCHAR(64) COMMENT '리프레시 토큰에 담긴 문자열 정보'")
    val subject: String,

    @Column(name = "member_number", nullable = false, columnDefinition = "VARCHAR(32) COMMENT '회원번호'")
    val memberNumber: String,

    @Column(name = "expire_datetime", nullable = false, columnDefinition = "datetime(6) COMMENT '만료기간'")
    val expireDateTime: LocalDateTime,
) : BaseEntity()
