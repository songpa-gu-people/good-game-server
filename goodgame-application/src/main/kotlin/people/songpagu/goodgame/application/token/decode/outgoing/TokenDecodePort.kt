package people.songpagu.goodgame.application.token.decode.outgoing

import java.time.LocalDateTime

interface TokenDecodePort {
    fun decode(jwt: String): JwtDecodeAnswer

    sealed class JwtDecodeAnswer {
        data class Success(val jwtToken: JwtToken) : JwtDecodeAnswer()

        sealed class Failure(val message: String) : JwtDecodeAnswer() {
            class Expire(message: String) : Failure(message)
            class MalForm(message: String) : Failure(message)
            class Error(message: String) : Failure(message)
        }

    }

    data class JwtToken(
        val memberNumber: String,
        val issueDateTime: LocalDateTime,
        val expireDateTime: LocalDateTime,
    )
}
