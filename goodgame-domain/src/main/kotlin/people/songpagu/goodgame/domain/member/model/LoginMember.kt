package people.songpagu.goodgame.domain.member.model

import java.time.LocalDateTime

data class LoginMember(
    val memberNumber: String,
    var expireDatetime: LocalDateTime,
    var createdDateTime: LocalDateTime,
) {
    companion object {
        const val KEY: String = "goodGameLoginMember"
        const val EXPIRE_PERIOD: Long = 14
    }
}
