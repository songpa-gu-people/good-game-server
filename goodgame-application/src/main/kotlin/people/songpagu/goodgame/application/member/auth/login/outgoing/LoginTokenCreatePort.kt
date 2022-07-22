package people.songpagu.goodgame.application.member.auth.login.outgoing

import people.songpagu.goodgame.application.member.auth.login.incoming.LoginTokenCreateUseCase.LoginTokenCreateCommand

interface LoginTokenCreatePort {
    fun create(command: LoginTokenCreateCommand)
}
