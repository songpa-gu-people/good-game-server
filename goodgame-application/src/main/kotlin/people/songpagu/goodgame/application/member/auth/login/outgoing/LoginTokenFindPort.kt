package people.songpagu.goodgame.application.member.auth.login.outgoing

interface LoginTokenFindPort {
    fun findAllBy(memberNumber: String): List<LoginTokenFindAnswer>

    data class LoginTokenFindAnswer(
        val id: Long,
        val memberNumber: String,
        val refreshToken: String,
    )
}
