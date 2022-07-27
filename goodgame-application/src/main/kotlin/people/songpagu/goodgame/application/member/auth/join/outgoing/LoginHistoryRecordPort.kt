package people.songpagu.goodgame.application.member.auth.join.outgoing

import people.songpagu.goodgame.domain.member.type.LoginHistoryType

fun interface LoginHistoryRecordPort {
    fun record(command: LoginHistoryRecordCommand)

    data class LoginHistoryRecordCommand(
        val memberNumber: String,
        val type: LoginHistoryType,
    )
}
