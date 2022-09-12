package people.songpagu.goodgame.application.member.auth.login.outgoing

interface LoginMemberNumberFindPort {
    fun findMemberNumberBy(subjectOfToken: String): String
}
