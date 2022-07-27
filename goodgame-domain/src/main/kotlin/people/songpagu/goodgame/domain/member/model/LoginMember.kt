package people.songpagu.goodgame.domain.member.model

import java.time.LocalDateTime

data class LoginMember(
    val memberNumber: String,
    val createdDateTime: LocalDateTime = LocalDateTime.now(),
    val expireDatetime: LocalDateTime = createdDateTime.plusDays(EXPIRE_PERIOD),
) {
    companion object {
        const val KEY: String = "goodGameLoginMember"
        const val EXPIRE_PERIOD: Long = 14
    }
}
