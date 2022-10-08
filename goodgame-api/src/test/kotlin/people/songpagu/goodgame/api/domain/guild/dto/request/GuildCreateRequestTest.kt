package people.songpagu.goodgame.api.domain.guild.dto.request

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import people.songpagu.goodgame.api.test.DtoValidationTestContext
import people.songpagu.goodgame.jpa.domain.guild.entity.GUILD_NAME_MAX_LENGTH
import people.songpagu.goodgame.jpa.lifecycle.common.CArbitraries

internal class GuildCreateRequestTest : DtoValidationTestContext() {

    @Test
    fun 이상_없는_dto() {
        val guildCreateRequest = GuildCreateRequest(CArbitraries.strings(GUILD_NAME_MAX_LENGTH))
        assertValidDto(guildCreateRequest)
    }

    @DisplayName("길드 이름은 $GUILD_NAME_MAX_LENGTH 글자를 넘길수 없다.")
    @Test
    fun name() {
        //given
        var guildName = "a"
        for (i in 1..GUILD_NAME_MAX_LENGTH) {
            guildName += "a"
        }

        //when
        val guildCreateRequest = GuildCreateRequest(guildName)

        //then
        Assertions.assertThat(getErrorMessagesFromDto(guildCreateRequest))
            .containsExactly("길드 이름이 너무 깁니다.")
    }
}