package people.songpagu.goodgame.security.domain.oauth.handler.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import people.songpagu.goodgame.application.member.auth.login.incoming.LoginTokenCreateUseCase
import people.songpagu.goodgame.application.member.auth.login.incoming.LoginTokenCreateUseCase.LoginTokenCreateCommand
import people.songpagu.goodgame.application.member.auth.login.incoming.LoginTokenRemoveUseCase
import people.songpagu.goodgame.domain.member.model.LoginMember
import people.songpagu.goodgame.domain.token.type.LoginTokenType
import java.util.UUID

@Service
@Transactional
class OAuth2TokenService(
    private val loginTokenRemoveUseCase: LoginTokenRemoveUseCase,
    private val loginTokenCreateUseCase: LoginTokenCreateUseCase,
) {
    fun deleteLoginToken(loginMember: LoginMember) {
        loginTokenRemoveUseCase.remove(loginMember)
    }

    fun generateLoginToken(loginMember: LoginMember): String {
        val subject = UUID.randomUUID().toString()

        val command = LoginTokenCreateCommand(
            loginTokenType = LoginTokenType.JWT,
            subject = subject,
            memberNumber = loginMember.memberNumber,
            expireDateTime = loginMember.expireDatetime,
        )

        return loginTokenCreateUseCase.create(command)
    }
}
