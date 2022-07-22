package people.songpagu.goodgame.application.token.decode.service

import people.songpagu.goodgame.application.token.decode.incoming.TokenAuthenticateUseCase
import people.songpagu.goodgame.application.token.decode.outgoing.TokenDecodePort
import people.songpagu.goodgame.application.token.decode.outgoing.TokenDecodePort.JwtDecodeAnswer
import java.time.LocalDateTime

class TokenAuthenticateService(
    private val tokenDecodePort: TokenDecodePort,
) : TokenAuthenticateUseCase {
    override fun validate(token: String, validateTime: LocalDateTime): String {
        val answer: JwtDecodeAnswer = tokenDecodePort.decode(token)

        if (answer is JwtDecodeAnswer.Failure) {
            throw IllegalAccessException(answer.message)
        }

        val successAnswer = answer as JwtDecodeAnswer.Success

        if (validateTime.isAfter(successAnswer.jwtToken.expireDateTime)) {
            throw IllegalAccessException("엑세스 토큰 만료")
        }

        return successAnswer.jwtToken.memberNumber
    }
}
