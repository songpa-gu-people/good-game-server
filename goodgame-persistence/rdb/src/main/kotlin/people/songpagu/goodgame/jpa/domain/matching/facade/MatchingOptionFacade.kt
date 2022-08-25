package people.songpagu.goodgame.jpa.domain.matching.facade

import org.springframework.stereotype.Repository
import people.songpagu.goodgame.application.matching.option.outgoing.MatchingOptionFindPort
import people.songpagu.goodgame.domain.matching.option.model.MatchingOption
import people.songpagu.goodgame.jpa.domain.matching.repository.MatchingOptionJpaRepository

@Repository
class MatchingOptionFacade(
    private val matchingOptionJpaRepository: MatchingOptionJpaRepository
) : MatchingOptionFindPort {
    override fun findByMemberNumber(memberNumber: String): MatchingOption? {
        val entity = matchingOptionJpaRepository.findByMemberNumber(memberNumber)
        return entity?.let {
            MatchingOption(entity.memberNumber, entity.districts, entity.genders)
        }
    }

}