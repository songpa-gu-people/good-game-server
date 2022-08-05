package people.songpagu.goodgame.security.domain.oauth.handler.facade

import org.springframework.stereotype.Component
import people.songpagu.goodgame.application.token.generate.incoming.TokenGenerateUseCase
import people.songpagu.goodgame.application.token.generate.incoming.TokenGenerateUseCase.TokenIssueCommand
import people.songpagu.goodgame.domain.member.model.LoginMember
import people.songpagu.goodgame.security.domain.oauth.handler.service.OAuth2TokenService

@Component
class OAuth2SuccessFacade(
    private val tokenGenerateUseCase: TokenGenerateUseCase,
    private val oAuth2TokenService: OAuth2TokenService,
) {

    fun generateRefreshToken(loginMember: LoginMember): String {
        //기존 refreshToken 제거
        oAuth2TokenService.deleteLoginToken(loginMember)

        //신규 refreshToken 발급
        val refreshTokenSubject: String = oAuth2TokenService.generateLoginToken(loginMember)

        //refreshJwt 발급
        val issueCommand = TokenIssueCommand(
            payload = refreshTokenSubject,
            issueDateTime = loginMember.createdDateTime,
            expireDateTime = loginMember.expireDatetime,
        )
        return tokenGenerateUseCase.issue(issueCommand)
    }
}
