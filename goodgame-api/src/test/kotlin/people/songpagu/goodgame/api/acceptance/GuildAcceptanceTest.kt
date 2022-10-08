package people.songpagu.goodgame.api.acceptance

import com.fasterxml.jackson.core.type.TypeReference
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import people.songpagu.goodgame.api.config.common.response.ApiResponse
import people.songpagu.goodgame.api.domain.guild.GuildControllerPath
import people.songpagu.goodgame.api.domain.guild.dto.request.GuildCreateRequest
import people.songpagu.goodgame.api.test.GoodGameApiTestFixtureBundle
import people.songpagu.goodgame.domain.guild.type.GuildMemberRole
import people.songpagu.goodgame.jpa.domain.guild.entity.GUILD_NAME_MAX_LENGTH
import people.songpagu.goodgame.jpa.domain.member.entity.MemberEntity
import people.songpagu.goodgame.jpa.lifecycle.common.CArbitraries

class GuildAcceptanceTest : GoodGameApiTestFixtureBundle() {

    /**
     * 길드 생성
     * 길드 초대(추가 예정)
     * 길드 초대 수락(추가 예정)
     */
    @DisplayName("길드 관리 인수 테스트")
    @Test
    fun name() {
        //given
        val memberEntity: MemberEntity = initKakaoMember()
        val token = createToken(memberEntity)

        //길드 생성 요청
        val createRequest = GuildCreateRequest(CArbitraries.strings(GUILD_NAME_MAX_LENGTH))
        길드_생성_요청(token, createRequest)

        길드_생성됨(memberEntity.memberNumber)
    }

    private fun 길드_생성됨(memberNumber: String) {
        val transaction = getTransaction()

        val guilds = guildJpaRepository.findAll()
        assertThat(guilds).hasSize(1)
        assertThat(guilds[0].guildMembers[0].guildMemberRole).isEqualTo(GuildMemberRole.MASTER)
        assertThat(guilds[0].guildMembers[0].memberNumber).isEqualTo(memberNumber)

        transactionManager.commit(transaction)
    }

    private fun 길드_생성_요청(
        token: String, createRequest: GuildCreateRequest
    ): ApiResponse.Ok<Void> {
        return postApi(path = GuildControllerPath.createGuild,
            token = token,
            body = createRequest,
            responseType = object : TypeReference<ApiResponse.Ok<Void>>() {})
    }
}