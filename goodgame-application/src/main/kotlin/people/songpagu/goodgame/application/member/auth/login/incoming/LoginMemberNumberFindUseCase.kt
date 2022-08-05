package people.songpagu.goodgame.application.member.auth.login.incoming

interface LoginMemberNumberFindUseCase {
    fun findMemberNumberBy(refreshToken: String): String
}
