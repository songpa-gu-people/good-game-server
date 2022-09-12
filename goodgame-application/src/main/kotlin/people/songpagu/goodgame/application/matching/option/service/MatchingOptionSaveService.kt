package people.songpagu.goodgame.application.matching.option.service

import people.songpagu.goodgame.application.matching.option.incoming.MatchingOptionSaveUseCase
import people.songpagu.goodgame.application.matching.option.incoming.MatchingOptionSaveUseCase.MatchingOptionSaveCommand
import people.songpagu.goodgame.application.matching.option.outgoing.MatchingOptionFindPort
import people.songpagu.goodgame.application.matching.option.outgoing.UpdateMatchingOptionStatePort
import people.songpagu.goodgame.application.matching.option.outgoing.UpdateMatchingOptionStatePort.UpdateMatchingOptionStateCommand

class MatchingOptionSaveService(
    private val updateMatchingOptionStatePort: UpdateMatchingOptionStatePort,
    private val matchingOptionFindPort: MatchingOptionFindPort,
) : MatchingOptionSaveUseCase {

    override fun saveOrUpdate(
        memberNumber: String,
        command: MatchingOptionSaveCommand
    ) {
        val matchingOptionEntityId = matchingOptionFindPort.findByMemberNumber(memberNumber)?.id
        updateMatchingOptionStatePort.save(
            UpdateMatchingOptionStateCommand(
                matchingOptionEntityId = matchingOptionEntityId,
                memberNumber = memberNumber,
                districts = command.districts,
                genders = command.genders
            )
        )
    }
}