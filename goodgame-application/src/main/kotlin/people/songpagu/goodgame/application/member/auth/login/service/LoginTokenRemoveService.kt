package people.songpagu.goodgame.application.member.auth.login.service

import people.songpagu.goodgame.application.member.auth.login.incoming.LoginTokenRemoveUseCase
import people.songpagu.goodgame.application.member.auth.login.outgoing.LoginTokenFindPort
import people.songpagu.goodgame.application.member.auth.login.outgoing.LoginTokenFindPort.LoginTokenFindAnswer
import people.songpagu.goodgame.application.member.auth.login.outgoing.LoginTokenRemovePort
import people.songpagu.goodgame.domain.member.model.LoginMember

class LoginTokenRemoveService(
    private val loginTokenFindPort: LoginTokenFindPort,
    private val loginTokenRemovePort: LoginTokenRemovePort,
) : LoginTokenRemoveUseCase {
    override fun remove(loginMember: LoginMember) {
        val loginTokens: List<LoginTokenFindAnswer> = loginTokenFindPort.findAllBy(loginMember.memberNumber)

        val removeCommands = loginTokens.map { it.id }

        loginTokenRemovePort.deleteAllBy(removeCommands)
    }
}
