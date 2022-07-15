package people.songpagu.goodgame.jpa.domain.token.entity

import people.songpagu.goodgame.domain.token.type.LoginTokenType
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class LoginTokenEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "token_type", nullable = false, columnDefinition = "VARCHAR(20) COMMENT '토큰 타입'")
    val tokenType: LoginTokenType,

    val refreshToken: String,

    val expireDateTime: LocalDateTime,
) {
}
