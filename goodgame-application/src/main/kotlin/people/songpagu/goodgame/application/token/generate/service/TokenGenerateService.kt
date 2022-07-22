package people.songpagu.goodgame.application.token.generate.service

import people.songpagu.goodgame.application.token.generate.incoming.TokenGenerateUseCase
import people.songpagu.goodgame.application.token.generate.incoming.TokenGenerateUseCase.TokenIssueCommand
import people.songpagu.goodgame.application.token.generate.outgoing.TokenGeneratePort

class TokenGenerateService(
    private val tokenGeneratePort: TokenGeneratePort,
) : TokenGenerateUseCase {
    override fun issue(tokenIssueCommand: TokenIssueCommand): String {
        return tokenGeneratePort.issue(tokenIssueCommand)
    }
}
