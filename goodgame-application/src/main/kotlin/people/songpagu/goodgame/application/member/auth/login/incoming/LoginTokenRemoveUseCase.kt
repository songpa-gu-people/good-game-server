package people.songpagu.goodgame.application.member.auth.login.incoming

import people.songpagu.goodgame.domain.member.model.LoginMember

interface LoginTokenRemoveUseCase {

    fun remove(loginMember: LoginMember)
}
