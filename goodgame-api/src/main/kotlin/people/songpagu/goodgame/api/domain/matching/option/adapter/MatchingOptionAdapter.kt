package people.songpagu.goodgame.api.domain.matching.option.adapter

import people.songpagu.goodgame.api.config.layer.Adapter
import people.songpagu.goodgame.api.domain.matching.option.dto.MatchingOptionSaveOrUpdateRequest
import people.songpagu.goodgame.application.matching.option.incoming.MatchingOptionFindUseCase
import people.songpagu.goodgame.application.matching.option.incoming.MatchingOptionSaveUseCase

@Adapter
class MatchingOptionAdapter(
    private val matchingOptionFindUseCase: MatchingOptionFindUseCase,
    private val matchingOptionSaveUseCase: MatchingOptionSaveUseCase
) {
    fun findMatchingOptionBy(memberNumber: String): MatchingOptionFindUseCase.MatchingOptionAnswer {
        return matchingOptionFindUseCase.findMatchingOptionBy(memberNumber)
    }

    fun saveOrUpdate(memberNumber: String, request: MatchingOptionSaveOrUpdateRequest) {
        return matchingOptionSaveUseCase.save(
            memberNumber,
            MatchingOptionSaveUseCase.MatchingOptionSaveCommand(request.districts, request.genders)
        )
    }
}
