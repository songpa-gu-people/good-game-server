package people.songpagu.goodgame.api.domain.matching.option

import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import people.songpagu.goodgame.api.domain.response.ApiResponse
import people.songpagu.goodgame.security.domain.member.UserDetailsImpl

@RestController
class MatchingOptionController {

    // 로그인 한 유저를 가져오는 기능까지 구현
    @GetMapping("/api/v1/matching-option")
    fun getMyMatchingOption(
        @AuthenticationPrincipal member: UserDetailsImpl
    ): ApiResponse<String> {
        return ApiResponse.Ok(member.memberNumber)
    }

}