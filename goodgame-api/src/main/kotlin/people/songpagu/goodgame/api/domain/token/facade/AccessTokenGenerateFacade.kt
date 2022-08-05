package people.songpagu.goodgame.api.domain.token.facade

import org.springframework.stereotype.Component
import people.songpagu.goodgame.application.member.auth.login.incoming.LoginMemberNumberFindUseCase
import people.songpagu.goodgame.application.token.decode.incoming.TokenAuthenticateUseCase
import people.songpagu.goodgame.application.token.generate.incoming.TokenGenerateUseCase
import people.songpagu.goodgame.application.token.generate.incoming.TokenGenerateUseCase.TokenIssueCommand
import java.time.LocalDateTime

@Component
class AccessTokenGenerateFacade(
    private val tokenAuthenticateUseCase: TokenAuthenticateUseCase,
    private val tokenGenerateUseCase: TokenGenerateUseCase,
    private val loginMemberNumberFindUseCase: LoginMemberNumberFindUseCase
) {
    fun issue(refreshToken: String, issueDateTime: LocalDateTime = LocalDateTime.now()): String {
        val subjectToken = tokenAuthenticateUseCase.validate(refreshToken, LocalDateTime.now())
        val memberNumber = loginMemberNumberFindUseCase.findMemberNumberBy(subjectToken);

        val command = TokenIssueCommand.accessToken(payload = memberNumber)

        return tokenGenerateUseCase.issue(command)
    }
}
