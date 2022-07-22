package people.songpagu.goodgame.application.token.generate.outgoing

import people.songpagu.goodgame.application.token.generate.incoming.TokenGenerateUseCase

interface TokenGeneratePort {
    fun issue(tokenIssueCommand: TokenGenerateUseCase.TokenIssueCommand): String
}
