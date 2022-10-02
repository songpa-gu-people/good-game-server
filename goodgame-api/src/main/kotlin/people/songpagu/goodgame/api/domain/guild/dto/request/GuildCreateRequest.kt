package people.songpagu.goodgame.api.domain.guild.dto.request

import javax.validation.constraints.NotEmpty


data class GuildCreateRequest(
    @NotEmpty(message = "길드의 이름을 입력해주세요.")
    val guildName: String,
)
