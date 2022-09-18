package people.songpagu.goodgame.api.domain.matching.option.handler

import people.songpagu.goodgame.api.config.layer.Handler
import people.songpagu.goodgame.api.domain.matching.option.adapter.MatchingOptionAdapter
import people.songpagu.goodgame.api.domain.matching.option.dto.request.MatchingOptionSaveOrUpdateRequest
import people.songpagu.goodgame.application.matching.option.incoming.MatchingOptionFindUseCase

@Handler
class MatchingOptionHandler(
    private val matchingOptionAdapter: MatchingOptionAdapter
) {
    fun findMatchingOptionBy(memberNumber: String): MatchingOptionFindUseCase.MatchingOptionAnswer {
        return matchingOptionAdapter.findMatchingOptionBy(memberNumber)
    }

    fun saveOrUpdateMatchingOption(memberNumber: String, request: MatchingOptionSaveOrUpdateRequest) {
        return matchingOptionAdapter.saveOrUpdate(memberNumber, request)
    }
}
