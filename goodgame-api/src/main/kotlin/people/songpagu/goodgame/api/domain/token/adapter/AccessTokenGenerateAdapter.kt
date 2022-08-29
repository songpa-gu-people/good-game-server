package people.songpagu.goodgame.api.domain.token.adapter

import org.springframework.transaction.annotation.Transactional
import people.songpagu.goodgame.api.config.layer.Adapter
import people.songpagu.goodgame.application.member.auth.login.incoming.LoginMemberNumberFindUseCase
import people.songpagu.goodgame.application.token.decode.incoming.TokenAuthenticateUseCase
import people.songpagu.goodgame.application.token.generate.incoming.TokenGenerateUseCase
import people.songpagu.goodgame.application.token.generate.incoming.TokenGenerateUseCase.TokenIssueCommand
import java.time.LocalDateTime

@Adapter
class AccessTokenGenerateAdapter(
    private val tokenAuthenticateUseCase: TokenAuthenticateUseCase,
    private val tokenGenerateUseCase: TokenGenerateUseCase,
    private val loginMemberNumberFindUseCase: LoginMemberNumberFindUseCase
) {
    @Transactional
    fun issue(refreshToken: String, issueDateTime: LocalDateTime): String {
        val subjectToken = tokenAuthenticateUseCase.validate(refreshToken, issueDateTime)
        val memberNumber = loginMemberNumberFindUseCase.findMemberNumberBy(subjectToken)

        val command = TokenIssueCommand.accessToken(payload = memberNumber)

        return tokenGenerateUseCase.issue(command)
    }
}
