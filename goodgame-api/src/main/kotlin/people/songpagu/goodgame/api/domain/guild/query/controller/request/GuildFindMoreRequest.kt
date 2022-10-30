package people.songpagu.goodgame.api.domain.guild.query.controller.request

import javax.validation.constraints.Max
import javax.validation.constraints.Min

data class GuildFindMoreRequest(
    val startId: Long? = null,

    @field: Max(value = 1000)
    @field: Min(value = 1)
    val size: Long = DEFAULT_PAGE_SIZE,

    val guildName: String? = null,
) {
    companion object {
        const val DEFAULT_PAGE_SIZE: Long = 20
    }
}
