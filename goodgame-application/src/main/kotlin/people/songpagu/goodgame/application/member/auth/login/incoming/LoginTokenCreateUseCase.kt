package people.songpagu.goodgame.application.member.auth.login.incoming

import people.songpagu.goodgame.domain.token.type.LoginTokenType
import java.time.LocalDateTime

interface LoginTokenCreateUseCase {
    fun create(command: LoginTokenCreateCommand): String

    data class LoginTokenCreateCommand(
        val memberNumber: String,
        val loginTokenType: LoginTokenType,
        val subject: String,
        val expireDateTime: LocalDateTime,
    )
}
