package people.songpagu.goodgame.domain.member.model

import java.time.LocalDateTime

data class LoginMember(
    val memberId: Long,
    val memberNumber: String,
    var expireDatetime: LocalDateTime,
    var createdDateTime: LocalDateTime,
)
