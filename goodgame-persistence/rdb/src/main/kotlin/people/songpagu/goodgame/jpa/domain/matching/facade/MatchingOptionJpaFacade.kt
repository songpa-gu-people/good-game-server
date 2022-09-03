package people.songpagu.goodgame.jpa.domain.matching.facade

import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import people.songpagu.goodgame.application.matching.option.outgoing.MatchingOptionFindPort
import people.songpagu.goodgame.application.matching.option.outgoing.UpdateMatchingOptionStatePort
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
            MatchingOption(entity.memberNumber, entity.districts.values, entity.genders.values)
        }
    }

    @Transactional
    override fun update(command: UpdateMatchingOptionStatePort.UpdateMatchingOptionStateCommand) {
        val memberNumber: String = command.memberNumber
        val districts: List<District> = command.districts
        val genders: List<Gender> = command.genders
        val matchingOption: MatchingOptionEntity = (matchingOptionJpaRepository.findByMemberNumber(memberNumber)
            ?: MatchingOptionEntity.create(memberNumber, districts, genders))

        matchingOption.update(districts, genders)
        matchingOptionJpaRepository.save(matchingOption)
    }


}
