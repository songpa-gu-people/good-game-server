package people.songpagu.goodgame.application.member.auth.login.service

import people.songpagu.goodgame.application.member.auth.login.incoming.LoginMemberNumberFindUseCase
import people.songpagu.goodgame.application.member.auth.login.outgoing.LoginMemberNumberFindPort

class LoginMemberNumberFindService(
    private val loginMemberNumberFindPort: LoginMemberNumberFindPort
) : LoginMemberNumberFindUseCase {
    override fun findMemberNumberBy(refreshToken: String): String {
        return loginMemberNumberFindPort.findMemberNumber(refreshToken)
    }
}