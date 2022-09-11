package people.songpagu.goodgame.application.member.auth.login.incoming

interface LoginMemberNumberFindUseCase {
    fun findMemberNumberBy(subjectOfToken: String): String
}
