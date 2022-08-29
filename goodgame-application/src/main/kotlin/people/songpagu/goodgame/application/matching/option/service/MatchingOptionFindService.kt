package people.songpagu.goodgame.application.matching.option.service

import people.songpagu.goodgame.application.matching.option.incoming.MatchingOptionFindUseCase
import people.songpagu.goodgame.application.matching.option.outgoing.MatchingOptionFindPort

class MatchingOptionFindService(
    private val matchingOptionFindPort: MatchingOptionFindPort
) : MatchingOptionFindUseCase {
    override fun findMatchingOptionBy(memberNumber: String): MatchingOptionFindUseCase.MatchingOptionAnswer {
        val matchingOption = matchingOptionFindPort.findByMemberNumber(memberNumber)
        return matchingOption?.let { MatchingOptionFindUseCase.MatchingOptionAnswer.of(matchingOption) }
            ?: MatchingOptionFindUseCase.MatchingOptionAnswer.ofEmpty(memberNumber)
    }
}
