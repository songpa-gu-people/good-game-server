package people.songpagu.goodgame.application.member.auth.login.service

import people.songpagu.goodgame.application.member.auth.login.incoming.LoginTokenCreateUseCase
import people.songpagu.goodgame.application.member.auth.login.incoming.LoginTokenCreateUseCase.LoginTokenCreateCommand
import people.songpagu.goodgame.application.member.auth.login.outgoing.LoginTokenCreatePort

class LoginTokenCreateService(
    private val loginTokenCreatePort: LoginTokenCreatePort,
) : LoginTokenCreateUseCase {
    override fun create(command: LoginTokenCreateCommand): String {
        loginTokenCreatePort.create(command)

        return command.subject
    }
}
