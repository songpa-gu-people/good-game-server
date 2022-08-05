package people.songpagu.goodgame.application.token.generate.incoming

import java.time.LocalDateTime

interface TokenGenerateUseCase {
    fun issue(tokenIssueCommand: TokenIssueCommand): String

    data class TokenIssueCommand(
        val payload: String,
        val issueDateTime: LocalDateTime,
        val expireDateTime: LocalDateTime,
    ) {
        companion object {
            fun accessToken(
                payload: String,
                issueDateTime: LocalDateTime = LocalDateTime.now(),
                expireDateTime: LocalDateTime = issueDateTime.plusSeconds(10),
            ): TokenIssueCommand {
                return TokenIssueCommand(
                    payload = payload,
                    issueDateTime = issueDateTime,
                    expireDateTime = expireDateTime
                )
            }
        }
    }
}
