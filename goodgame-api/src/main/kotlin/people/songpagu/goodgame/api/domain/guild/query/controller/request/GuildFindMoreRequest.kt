package people.songpagu.goodgame.api.domain.guild.query.controller.request

import javax.validation.constraints.Max
import javax.validation.constraints.Min

data class GuildFindMoreRequest(
    val pageNumber: Int = 0,

    @field: Max(value = 1000)
    @field: Min(value = 1)
    val size: Int = DEFAULT_PAGE_SIZE,

    val guildName: String? = null,
) {
    companion object {
        const val DEFAULT_PAGE_SIZE: Int = 20
    }
}
