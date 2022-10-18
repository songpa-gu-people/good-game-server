package people.songpagu.goodgame.api.domain.guild.dto.request

import javax.validation.constraints.Max
import javax.validation.constraints.Min

const val DEFAULT_PAGE_SIZE: Long = 20

data class GuildFindPageRequest(
    @Min(value = 1)
    val page: Long = 1L,
    @Min(value = 1)
    @Max(value = 1000)
    val size: Long = DEFAULT_PAGE_SIZE,
)
