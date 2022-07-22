package people.songpagu.infrastructure.jwt.incoming.jjwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jws
import io.jsonwebtoken.JwtParser
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.security.SignatureException
import people.songpagu.goodgame.application.token.decode.outgoing.TokenDecodePort.JwtDecodeAnswer
import people.songpagu.goodgame.application.token.decode.outgoing.TokenDecodePort.JwtDecodeAnswer.Failure
import people.songpagu.goodgame.application.token.decode.outgoing.TokenDecodePort.JwtDecodeAnswer.Failure.Error
import people.songpagu.goodgame.application.token.decode.outgoing.TokenDecodePort.JwtDecodeAnswer.Success
import people.songpagu.goodgame.application.token.decode.outgoing.TokenDecodePort.JwtToken
import people.songpagu.infrastructure.jwt.incoming.JwtTokenDecodeUseCase
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date
import javax.crypto.SecretKey

class JJwtTokenDecoder(
    private val key: SecretKey,
) : JwtTokenDecodeUseCase {
    override fun decode(jwt: String): JwtDecodeAnswer {
        val jwtParser: JwtParser = Jwts.parserBuilder()
            .setSigningKey(key)
            .build()

        val parseResult: Result<JwtToken> = runCatching {
            jwtParser.parseClaimsJws(jwt).toJwtToken()
        }

        return when (parseResult.isSuccess) {
            true -> Success(parseResult.getOrThrow())
            false -> parseResult.exceptionOrNull().parseException()
        }
    }

    private fun Jws<Claims>.toJwtToken(): JwtToken {
        return JwtToken(
            memberNumber = this.body.subject,
            issueDateTime = this.body.issuedAt.toLocalDateTime(),
            expireDateTime = this.body.expiration.toLocalDateTime(),
        )
    }

    private fun Date.toLocalDateTime(): LocalDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(this.time), ZoneId.of("Asia/Seoul"))
    private fun LocalDateTime.toDate(): Date = Date.from(this.atZone(ZoneId.of("Asia/Seoul")).toInstant())

    private fun Throwable?.parseException(): Failure {
        return when (this) {
            null -> Error("로그인 토큰 에러")
            is ExpiredJwtException -> Failure.Expire(this.message ?: "로그인 토큰 만료")
            is MalformedJwtException -> Failure.MalForm(this.message ?: "로그인 토큰 형식 에러")
            is SignatureException -> Failure.MalForm(this.message ?: "로그인 토큰 인증 에러")
            else -> Error(this.message ?: "로그인 토큰 에러")
        }
    }
}
