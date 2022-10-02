package people.songpagu.goodgame.api.domain.guild.dto.request

import people.songpagu.goodgame.jpa.domain.guild.entity.GUILD_NAME_MAX_LENGTH
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

data class GuildCreateRequest(
    @field: Size(max = GUILD_NAME_MAX_LENGTH, message = "길드 이름이 너무 깁니다.")
    @field: NotEmpty(message = "길드의 이름을 입력해주세요.")
    val guildName: String,
)
