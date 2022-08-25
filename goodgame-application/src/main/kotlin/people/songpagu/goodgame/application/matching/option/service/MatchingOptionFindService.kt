package people.songpagu.goodgame.application.matching.option.service

import people.songpagu.goodgame.application.matching.option.incoming.MatchingOptionFindUseCase
import people.songpagu.goodgame.application.matching.option.outgoing.MatchingOptionFindPort

class MatchingOptionFindService(
    private val matchingOptionFindPort: MatchingOptionFindPort
) : MatchingOptionFindUseCase {
    override fun findMatchingOptionBy(memberNumber: String): MatchingOptionFindUseCase.MatchingOptionResponse {
        val matchingOption = matchingOptionFindPort.findByMemberNumber(memberNumber)
        return matchingOption?.let {
            MatchingOptionFindUseCase.MatchingOptionResponse.of(matchingOption)
        }
            ?: MatchingOptionFindUseCase.MatchingOptionResponse.ofEmpty()
    }
}