package people.songpagu.goodgame.api.domain.member

import com.fasterxml.jackson.core.type.TypeReference
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import people.songpagu.goodgame.api.config.common.response.ApiResponse
import people.songpagu.goodgame.api.domain.member.dto.response.MemberPrivacyResponse
import people.songpagu.goodgame.api.test.GoodGameApiTestFixtureBundle
import people.songpagu.goodgame.domain.member.type.Gender
import people.songpagu.goodgame.jpa.domain.member.entity.MemberEntity

internal class MemberPrivacyControllerTest : GoodGameApiTestFixtureBundle() {

    @DisplayName("나의 memberPrivacy 정보 요청 api 테스트")
    @Test
    internal fun getMyMemberPrivacy() {
        val gender = Gender.WOMAN
        val member: MemberEntity = initKakaoMember(gender)
        val token = createToken(member)

        val res = 나의_memberPrivacy_정보_요청(token)

        Assertions.assertThat(res.data?.memberNumber).isEqualTo(member.memberNumber)
        Assertions.assertThat(res.data?.gender).isEqualTo(gender)
        Assertions.assertThat(res.data?.email).isNotBlank
    }

    private fun 나의_memberPrivacy_정보_요청(token: String) = getApi(
        path = MemberPrivacyControllerPath.getMyMemberPrivacy,
        token = token,
        responseType = object : TypeReference<ApiResponse.Ok<MemberPrivacyResponse>>() {})
}