package people.songpagu.goodgame.api.acceptance

import com.fasterxml.jackson.core.type.TypeReference
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import people.songpagu.goodgame.api.config.common.response.ApiResponse
import people.songpagu.goodgame.api.domain.matching.option.MatchingOptionControllerPath
import people.songpagu.goodgame.api.domain.matching.option.dto.request.MatchingOptionSaveOrUpdateRequest
import people.songpagu.goodgame.api.domain.matching.option.dto.response.MatchingOptionResponse
import people.songpagu.goodgame.api.test.GoodGameApiTestFixtureBundle
import people.songpagu.goodgame.domain.matching.option.type.District
import people.songpagu.goodgame.domain.member.type.Gender
import people.songpagu.goodgame.jpa.domain.member.entity.MemberEntity

class MatchingOptionAcceptanceTest : GoodGameApiTestFixtureBundle() {

    /**
     * 매칭 옵션 저장
     * 매칭 옵션 조회
     * 매칭 옵션 수정
     */
    @DisplayName("매칭옵션 관리 인수 테스트")
    @Test
    fun test1() {
        //given
        val memberEntity: MemberEntity = initKakaoMember()
        val token = createToken(memberEntity)
        val memberNumber = memberEntity.memberNumber

        //저장 요청
        val saveRequest: MatchingOptionSaveOrUpdateRequest = MatchingOptionSaveOrUpdateRequest(
            listOf(District.DOBONG_GU, District.DONGDAEMUN_GU),
            listOf(Gender.MAN)
        )
        나의매칭옵션_저장_요청(token, saveRequest)

        //매칭 옵션 조회(저장 확인)
        val resWhenSave: ApiResponse.Ok<MatchingOptionResponse> = 나의_매칭옵션_요청(token)
        나의_매칭옵션_조회됨(resWhenSave, memberNumber, saveRequest)

        //수정 요청
        val updateRequest: MatchingOptionSaveOrUpdateRequest = MatchingOptionSaveOrUpdateRequest(
            listOf(District.GANGNAM_GU, District.GANGBUK_GU),
            listOf(Gender.WOMAN, Gender.MAN)
        )
        나의매칭옵션_저장_요청(token, updateRequest)

        //수정 확인
        val resWhenUpdate: ApiResponse.Ok<MatchingOptionResponse> = 나의_매칭옵션_요청(token)
        나의_매칭옵션_조회됨(resWhenUpdate, memberNumber, updateRequest)
    }

    @Suppress("NonAsciiCharacters")
    private fun 나의_매칭옵션_조회됨(
        res: ApiResponse.Ok<MatchingOptionResponse>,
        memberNumber: String,
        request: MatchingOptionSaveOrUpdateRequest
    ) {
        assertThat(res.data?.memberNumber).isEqualTo(memberNumber)
        assertThat(res.data?.districts).isEqualTo(request.districts)
        assertThat(res.data?.genders).isEqualTo(request.genders)
        assertThat(res.data?.exist).isEqualTo(true)
    }

    @Suppress("NonAsciiCharacters")
    private fun 나의매칭옵션_저장_요청(
        token: String,
        request: MatchingOptionSaveOrUpdateRequest
    ) {
        postApi(
            path = MatchingOptionControllerPath.saveOrUpdate,
            token = token,
            body = request,
            responseType = object : TypeReference<ApiResponse.Ok<Void>>() {},
        )
    }

    @Suppress("NonAsciiCharacters")
    private fun 나의_매칭옵션_요청(token: String): ApiResponse.Ok<MatchingOptionResponse> = getApi(
        path = MatchingOptionControllerPath.findMyMatchingOption,
        token = token,
        responseType = object : TypeReference<ApiResponse.Ok<MatchingOptionResponse>>() {},
    )
}
