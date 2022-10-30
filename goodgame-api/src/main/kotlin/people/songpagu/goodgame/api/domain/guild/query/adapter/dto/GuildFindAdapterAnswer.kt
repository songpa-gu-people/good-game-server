package people.songpagu.goodgame.api.domain.guild.query.adapter.dto

import people.songpagu.goodgame.application.guild.find.incoming.dto.GuildFindContent

data class GuildFindAdapterAnswer(
    val lastId: Long?,
    val contents: List<GuildFindContent>
) {
}
