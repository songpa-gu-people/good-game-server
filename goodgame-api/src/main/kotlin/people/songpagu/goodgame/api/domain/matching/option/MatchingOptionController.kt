package people.songpagu.goodgame.api.domain.matching.option

import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import people.songpagu.goodgame.api.domain.response.ApiResponse
import people.songpagu.goodgame.application.matching.option.incoming.MatchingOptionFindUseCase
import people.songpagu.goodgame.security.domain.member.UserDetailsImpl

@RestController
class MatchingOptionController(
    private val matchingOptionFindUseCase: MatchingOptionFindUseCase
) {

    @GetMapping(MatchingOptionControllerPath.findMyMatchingOption)
    fun getMyMatchingOption(
        @AuthenticationPrincipal member: UserDetailsImpl
    ): ApiResponse<MatchingOptionFindUseCase.MatchingOptionResponse> {
        val matchingOptionResponse = matchingOptionFindUseCase.findMatchingOptionBy(member.memberNumber)
        return ApiResponse.Ok(matchingOptionResponse)
    }

}