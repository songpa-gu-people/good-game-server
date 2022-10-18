package people.songpagu.goodgame.api.acceptance

import com.fasterxml.jackson.core.type.TypeReference
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.TestFactory
import people.songpagu.goodgame.api.config.common.response.ApiResponse
import people.songpagu.goodgame.api.domain.guild.GuildControllerPath
import people.songpagu.goodgame.api.domain.guild.dto.request.GuildCreateRequest
import people.songpagu.goodgame.api.domain.guild.dto.request.GuildFindPageRequest
import people.songpagu.goodgame.api.domain.guild.dto.response.GuildFindPageResponse
import people.songpagu.goodgame.api.test.GoodGameApiTestFixtureBundle
import people.songpagu.goodgame.common.page.PageData
import people.songpagu.goodgame.domain.guild.type.GuildMemberRole
import people.songpagu.goodgame.jpa.domain.guild.entity.GUILD_NAME_MAX_LENGTH
import people.songpagu.goodgame.jpa.domain.member.entity.MemberEntity
import people.songpagu.goodgame.jpa.lifecycle.common.CArbitraries
import java.util.stream.Stream

class GuildAcceptanceTest : GoodGameApiTestFixtureBundle() {

    /**
     * 길드 생성
     * 길드 초대(추가 예정)
     * 길드 초대 수락(추가 예정)
     */
    @DisplayName("길드 관리 인수 테스트")
    @TestFactory
    fun test1(): Stream<DynamicTest> {
        //given
        val memberEntity: MemberEntity = initKakaoMember()
        val token = createToken(memberEntity)

        val guildName = CArbitraries.strings(GUILD_NAME_MAX_LENGTH)

        return Stream.of(
            dynamicTest("길드 생성") {
                //given
                val createRequest = GuildCreateRequest(guildName)

                //when
                길드_생성_요청(token, createRequest)

                //then
                길드_생성됨(memberEntity.memberNumber)
            },
            dynamicTest("길드 조회") {
                //given
                val findRequest = GuildFindPageRequest(
                    page = 1,
                    size = 20,
                )

                //when
                val response: ApiResponse.Ok<PageData<GuildFindPageResponse>> = 길드_페이징_조회_요청(token = token, request = findRequest)

                //then
                assertThat(response.data!!.contents).hasSize(1)
                assertThat(response.data!!.contents[0].guildName).isEqualTo(guildName)
                assertThat(response.data!!.totalPages).isEqualTo(1)
                assertThat(response.data!!.totalElements).isEqualTo(1)
                assertThat(response.data!!.pageNumber).isEqualTo(1)
                assertThat(response.data!!.isFirst).isTrue
                assertThat(response.data!!.isLast).isTrue
                assertThat(response.data!!.isEmpty).isFalse
            }
        )
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
        return postApi(
            path = GuildControllerPath.Command.createGuild,
            token = token,
            body = createRequest,
            responseType = object : TypeReference<ApiResponse.Ok<Void>>() {},
        )
    }

    private fun 길드_페이징_조회_요청(
        token: String, request: GuildFindPageRequest
    ): ApiResponse.Ok<PageData<GuildFindPageResponse>> {
        return postApi(
            path = GuildControllerPath.Query.findGuilds,
            token = token,
            body = request,
            responseType = object : TypeReference<ApiResponse.Ok<PageData<GuildFindPageResponse>>>() {},
        )
    }
}
