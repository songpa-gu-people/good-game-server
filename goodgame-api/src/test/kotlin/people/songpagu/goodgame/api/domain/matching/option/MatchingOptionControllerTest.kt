package people.songpagu.goodgame.api.domain.matching.option

import com.fasterxml.jackson.core.type.TypeReference
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import people.songpagu.goodgame.api.config.common.response.ApiResponse
import people.songpagu.goodgame.api.domain.matching.option.response.MatchingOptionResponse
import people.songpagu.goodgame.api.test.GoodGameApiTest
import people.songpagu.goodgame.api.test.GoodGameApiTestContext
import people.songpagu.goodgame.application.token.generate.incoming.TokenGenerateUseCase
import people.songpagu.goodgame.domain.matching.option.type.District
import people.songpagu.goodgame.domain.member.type.Gender
import people.songpagu.goodgame.domain.member.type.LoginType
import people.songpagu.goodgame.jpa.domain.matching.entity.MatchingOptionEntity
import people.songpagu.goodgame.jpa.domain.matching.repository.MatchingOptionJpaRepository
import people.songpagu.goodgame.jpa.domain.member.entity.MemberEntity
import people.songpagu.goodgame.jpa.domain.member.repository.MemberJpaRepository
import java.util.UUID

@GoodGameApiTestContext
internal class MatchingOptionControllerTest(
    private val matchingOptionJpaRepository: MatchingOptionJpaRepository,
    private val memberJpaRepository: MemberJpaRepository,
    private val jwtTokenGenerateUseCase: TokenGenerateUseCase
) : GoodGameApiTest() {

    @DisplayName("멤버의 매칭 옵션 찾기 end to end 테스트")
    @Test
    internal fun test1() {
        //given
        val memberNumber: String = UUID.randomUUID().toString().replace("-", "")
        val memberEntity = MemberEntity.create(memberNumber, "authId", LoginType.KAKAO)
        memberJpaRepository.save(memberEntity)

        val districts = mutableListOf(District.DOBONG_GU, District.DONGDAEMUN_GU)
        val genders = mutableListOf(Gender.MAN)
        val matchingOptionEntity = MatchingOptionEntity.create(
            memberNumber = memberNumber,
            districts = districts,
            genders = genders,
        )
        matchingOptionJpaRepository.save(matchingOptionEntity)
        val token = jwtTokenGenerateUseCase.issue(TokenGenerateUseCase.TokenIssueCommand.accessToken(memberNumber))

        //when
        val res: ApiResponse.Ok<MatchingOptionResponse> = 나의_매칭옵션_요청(token)

        //then
        assertThat(res.data?.memberNumber).isEqualTo(memberNumber)
        assertThat(res.data?.districts).isEqualTo(districts)
        assertThat(res.data?.genders).isEqualTo(genders)
        assertThat(res.data?.exist).isEqualTo(true)
    }

    @Suppress("NonAsciiCharacters")
    private fun 나의_매칭옵션_요청(token: String): ApiResponse.Ok<MatchingOptionResponse> = getApi(
        path = MatchingOptionControllerPath.findMyMatchingOption,
        token = token,
        responseType = object : TypeReference<ApiResponse.Ok<MatchingOptionResponse>>() {},
    )
}
