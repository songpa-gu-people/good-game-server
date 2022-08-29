package people.songpagu.goodgame.api.domain.matching.option.adapter

import people.songpagu.goodgame.api.config.layer.Adapter
import people.songpagu.goodgame.application.matching.option.incoming.MatchingOptionFindUseCase

@Adapter
class MatchingOptionAdapter(
    private val matchingOptionFindUseCase: MatchingOptionFindUseCase
) {
    fun findMatchingOptionBy(memberNumber: String): MatchingOptionFindUseCase.MatchingOptionAnswer {
        return matchingOptionFindUseCase.findMatchingOptionBy(memberNumber)
    }
}
