package people.songpagu.goodgame.api.domain.matching.option.adapter

import people.songpagu.goodgame.api.config.layer.Adapter
import people.songpagu.goodgame.api.domain.matching.option.dto.request.MatchingOptionSaveOrUpdateRequest
import people.songpagu.goodgame.application.matching.option.incoming.MatchingOptionFindUseCase
import people.songpagu.goodgame.application.matching.option.incoming.MatchingOptionSaveUseCase
import people.songpagu.goodgame.application.matching.option.incoming.MatchingOptionSaveUseCase.MatchingOptionSaveCommand

@Adapter
class MatchingOptionAdapter(
    private val matchingOptionFindUseCase: MatchingOptionFindUseCase,
    private val matchingOptionSaveUseCase: MatchingOptionSaveUseCase
) {
    fun findMatchingOptionBy(memberNumber: String): MatchingOptionFindUseCase.MatchingOptionAnswer {
        return matchingOptionFindUseCase.findMatchingOptionBy(memberNumber)
    }

    fun saveOrUpdate(memberNumber: String, request: MatchingOptionSaveOrUpdateRequest) {
        val command = MatchingOptionSaveCommand(request.districts, request.genders)
        return matchingOptionSaveUseCase.saveOrUpdate(memberNumber, command)
    }
}
