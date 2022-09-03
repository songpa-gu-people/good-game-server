package people.songpagu.goodgame.application.matching.option.service

import people.songpagu.goodgame.application.matching.option.incoming.MatchingOptionSaveUseCase
import people.songpagu.goodgame.application.matching.option.incoming.MatchingOptionSaveUseCase.MatchingOptionSaveCommand
import people.songpagu.goodgame.application.matching.option.outgoing.UpdateMatchingOptionStatePort
import javax.transaction.Transactional

open class MatchingOptionSaveService(
    private val updateMatchingOptionStatePort: UpdateMatchingOptionStatePort
) : MatchingOptionSaveUseCase {

    @Transactional
    override fun save(
        memberNumber: String,
        command: MatchingOptionSaveCommand
    ) {
        updateMatchingOptionStatePort.update(
            UpdateMatchingOptionStatePort.UpdateMatchingOptionStateCommand(
                memberNumber,
                command.districts,
                command.genders
            )
        )
    }
}