package people.songpagu.goodgame.application.member.auth.login.outgoing

interface LoginTokenRemovePort {
    fun deleteAllBy(ids: List<Long>)
}
