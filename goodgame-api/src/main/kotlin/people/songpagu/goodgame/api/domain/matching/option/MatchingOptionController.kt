package people.songpagu.goodgame.api.domain.matching.option

import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import people.songpagu.goodgame.api.config.common.response.ApiResponse
import people.songpagu.goodgame.api.domain.matching.option.handler.MatchingOptionHandler
import people.songpagu.goodgame.api.domain.matching.option.response.MatchingOptionResponse
import people.songpagu.goodgame.application.matching.option.incoming.MatchingOptionFindUseCase.MatchingOptionAnswer
import people.songpagu.goodgame.security.domain.member.UserDetailsImpl

@RestController
class MatchingOptionController(
    private val matchingOptionHandler: MatchingOptionHandler
) {

    @GetMapping(MatchingOptionControllerPath.findMyMatchingOption)
    fun getMyMatchingOption(
        @AuthenticationPrincipal member: UserDetailsImpl
    ): ApiResponse<MatchingOptionResponse> {
        val matchingOptionAnswer: MatchingOptionAnswer = matchingOptionHandler.findMatchingOptionBy(member.memberNumber)

        val response = MatchingOptionResponse(matchingOptionAnswer)

        return ApiResponse.Ok(response)
    }

}
