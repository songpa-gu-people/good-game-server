package people.songpagu.goodgame.application.matching.option.service

import people.songpagu.goodgame.application.matching.option.incoming.MatchingOptionSaveUseCase
import people.songpagu.goodgame.application.matching.option.incoming.MatchingOptionSaveUseCase.MatchingOptionSaveCommand
import people.songpagu.goodgame.application.matching.option.outgoing.UpdateMatchingOptionStatePort

class MatchingOptionSaveService(
    private val updateMatchingOptionStatePort: UpdateMatchingOptionStatePort
) : MatchingOptionSaveUseCase {

    override fun saveOrUpdate(
        memberNumber: String,
        command: MatchingOptionSaveCommand
    ) {
        updateMatchingOptionStatePort.saveOrUpdate(
            UpdateMatchingOptionStatePort.UpdateMatchingOptionStateCommand(
                memberNumber,
                command.districts,
                command.genders
            )
        )
    }
}