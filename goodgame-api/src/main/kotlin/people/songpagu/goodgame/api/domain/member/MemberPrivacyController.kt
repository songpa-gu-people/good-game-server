package people.songpagu.goodgame.api.domain.member

import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import people.songpagu.goodgame.api.config.common.response.ApiResponse
import people.songpagu.goodgame.api.domain.member.dto.response.MemberPrivacyResponse
import people.songpagu.goodgame.api.domain.member.handler.MemberPrivacyHandler
import people.songpagu.goodgame.application.member.privacy.incoming.MemberPrivacyFindUseCase.MemberPrivacyAnswer
import people.songpagu.goodgame.security.domain.member.UserDetailsImpl

@RestController
class MemberPrivacyController(
    private val memberPrivacyHandler: MemberPrivacyHandler
) {
    @GetMapping(MemberPrivacyControllerPath.getMyMemberPrivacy)
    fun getMyMemberPrivacy(
        @AuthenticationPrincipal member: UserDetailsImpl
    ): ApiResponse<MemberPrivacyResponse> {
        val memberPrivacyAnswer: MemberPrivacyAnswer = memberPrivacyHandler.findMemberPrivacyBy(member.memberNumber)
        return ApiResponse.Ok(MemberPrivacyResponse(memberPrivacyAnswer))
    }
}