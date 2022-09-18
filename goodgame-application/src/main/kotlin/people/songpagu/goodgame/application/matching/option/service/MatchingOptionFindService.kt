package people.songpagu.goodgame.application.matching.option.service

import people.songpagu.goodgame.application.matching.option.incoming.MatchingOptionFindUseCase
import people.songpagu.goodgame.application.matching.option.outgoing.MatchingOptionFindPort
import people.songpagu.goodgame.domain.matching.option.model.MatchingOption

class MatchingOptionFindService(
    private val matchingOptionFindPort: MatchingOptionFindPort
) : MatchingOptionFindUseCase {
    override fun findMatchingOptionBy(memberNumber: String): MatchingOptionFindUseCase.MatchingOptionAnswer {
        val matchingOption: MatchingOption? = matchingOptionFindPort.findByMemberNumber(memberNumber)
        return matchingOption?.let { MatchingOptionFindUseCase.MatchingOptionAnswer.of(matchingOption) }
            ?: MatchingOptionFindUseCase.MatchingOptionAnswer.ofEmpty(memberNumber)
    }
}
