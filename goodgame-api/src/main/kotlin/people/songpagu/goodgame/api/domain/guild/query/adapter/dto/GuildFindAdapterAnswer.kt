package people.songpagu.goodgame.api.domain.guild.query.adapter.dto

import people.songpagu.goodgame.api.config.common.response.PageResponse
import people.songpagu.goodgame.application.guild.find.incoming.dto.GuildFindContent

data class GuildFindAdapterAnswer(
    val contents: List<GuildFindContent>,
    val pageResponse: PageResponse,
) {
}
