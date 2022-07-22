package people.songpagu.goodgame.jpa.domain.member.auth.login.facade

import org.springframework.stereotype.Repository
import people.songpagu.goodgame.application.member.auth.login.incoming.LoginTokenCreateUseCase.LoginTokenCreateCommand
import people.songpagu.goodgame.application.member.auth.login.outgoing.LoginTokenCreatePort
import people.songpagu.goodgame.application.member.auth.login.outgoing.LoginTokenFindPort
import people.songpagu.goodgame.application.member.auth.login.outgoing.LoginTokenFindPort.LoginTokenFindAnswer
import people.songpagu.goodgame.application.member.auth.login.outgoing.LoginTokenRemovePort
import people.songpagu.goodgame.jpa.domain.member.entity.LoginTokenEntity
import people.songpagu.goodgame.jpa.domain.member.repository.LoginTokenJpaRepository

@Repository
class LoginTokenJpaFacade(
    private val loginTokenJpaRepository: LoginTokenJpaRepository,
) : LoginTokenFindPort, LoginTokenRemovePort, LoginTokenCreatePort {

    override fun findAllBy(memberNumber: String): List<LoginTokenFindAnswer> {
        return loginTokenJpaRepository.findAllByMemberNumber(memberNumber)
            .map {
                LoginTokenFindAnswer(
                    id = requireNotNull(it.id),
                    memberNumber = it.memberNumber,
                    refreshToken = it.refreshToken,
                )
            }
    }

    override fun deleteAllBy(ids: List<Long>) {
        loginTokenJpaRepository.deleteAllById(ids)
    }

    override fun create(command: LoginTokenCreateCommand) {
        LoginTokenEntity(
            tokenType = command.loginTokenType,
            refreshToken = command.refreshToken,
            memberNumber = command.memberNumber,
            expireDateTime = command.expireDateTime,
        )
    }
}
