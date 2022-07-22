package people.songpagu.infrastructure.jwt.incoming.jjwt

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import people.songpagu.goodgame.application.token.generate.incoming.TokenGenerateUseCase.TokenIssueCommand
import people.songpagu.infrastructure.jwt.incoming.JwtTokenGenerateUseCase
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date
import javax.crypto.SecretKey

class JJwtTokenGenerateUseCase(
    private val key: SecretKey,
) : JwtTokenGenerateUseCase {

    override fun issue(tokenIssueCommand: TokenIssueCommand): String {
        return Jwts.builder()
            .setSubject(tokenIssueCommand.payload)
            .setIssuedAt(tokenIssueCommand.issueDateTime.toDate())
            .setExpiration(tokenIssueCommand.expireDateTime.toDate())
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()
    }

    private fun LocalDateTime.toDate(): Date = Date.from(this.atZone(ZoneId.of("Asia/Seoul")).toInstant())
}
