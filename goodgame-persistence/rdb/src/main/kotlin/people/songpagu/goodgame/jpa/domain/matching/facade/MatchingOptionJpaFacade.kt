package people.songpagu.goodgame.jpa.domain.matching.facade

import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import people.songpagu.goodgame.application.matching.option.outgoing.MatchingOptionFindPort
import people.songpagu.goodgame.application.matching.option.outgoing.UpdateMatchingOptionStatePort
import people.songpagu.goodgame.application.matching.option.outgoing.UpdateMatchingOptionStatePort.*
import people.songpagu.goodgame.domain.matching.option.model.MatchingOption
import people.songpagu.goodgame.domain.matching.option.type.District
import people.songpagu.goodgame.domain.member.type.Gender
import people.songpagu.goodgame.jpa.domain.matching.entity.MatchingOptionEntity
import people.songpagu.goodgame.jpa.domain.matching.repository.MatchingOptionJpaRepository

@Repository
class MatchingOptionJpaFacade(
    private val matchingOptionJpaRepository: MatchingOptionJpaRepository
) : MatchingOptionFindPort, UpdateMatchingOptionStatePort {

    override fun findByMemberNumber(memberNumber: String): MatchingOption? {
        val entity = matchingOptionJpaRepository.findByMemberNumber(memberNumber)
        return entity?.let {
            MatchingOption(entity.id, entity.memberNumber, entity.districts.values, entity.genders.values)
        }
    }

    @Transactional
    override fun save(command: UpdateMatchingOptionStateCommand) {
        val matchingOptionEntity: MatchingOptionEntity = MatchingOptionEntity.of(
            id = command.matchingOptionEntityId,
            memberNumber = command.memberNumber,
            districts = command.districts,
            genders = command.genders
        )
        matchingOptionJpaRepository.save(matchingOptionEntity)
    }
}
