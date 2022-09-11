package people.songpagu.goodgame.jpa.domain.member.facade

import org.springframework.stereotype.Repository
import people.songpagu.goodgame.application.member.auth.login.incoming.LoginTokenCreateUseCase.LoginTokenCreateCommand
import people.songpagu.goodgame.application.member.auth.login.outgoing.LoginMemberNumberFindPort
import people.songpagu.goodgame.application.member.auth.login.outgoing.LoginTokenCreatePort
import people.songpagu.goodgame.application.member.auth.login.outgoing.LoginTokenFindPort
import people.songpagu.goodgame.application.member.auth.login.outgoing.LoginTokenFindPort.LoginTokenFindAnswer
import people.songpagu.goodgame.application.member.auth.login.outgoing.LoginTokenRemovePort
import people.songpagu.goodgame.jpa.domain.member.entity.LoginTokenEntity
import people.songpagu.goodgame.jpa.domain.member.repository.LoginTokenJpaRepository
import javax.transaction.Transactional

@Repository
class LoginTokenJpaFacade(
    private val loginTokenJpaRepository: LoginTokenJpaRepository,
) : LoginTokenFindPort, LoginTokenRemovePort, LoginTokenCreatePort, LoginMemberNumberFindPort {

    override fun findAllBy(memberNumber: String): List<LoginTokenFindAnswer> {
        return loginTokenJpaRepository.findAllByMemberNumber(memberNumber)
            .map {
                LoginTokenFindAnswer(
                    id = requireNotNull(it.id),
                    memberNumber = it.memberNumber,
                    refreshToken = it.subject,
                )
            }
    }

    override fun deleteAllBy(ids: List<Long>) {
        loginTokenJpaRepository.deleteAllById(ids)
    }

    @Transactional
    override fun create(command: LoginTokenCreateCommand) {
        val loginTokenEntity = LoginTokenEntity(
            tokenType = command.loginTokenType,
            subject = command.subject,
            memberNumber = command.memberNumber,
            expireDateTime = command.expireDateTime,
        )
        loginTokenJpaRepository.save(loginTokenEntity)
    }

    override fun findMemberNumberBy(subjectOfToken: String): String {
        return loginTokenJpaRepository.findBySubject(subjectOfToken).memberNumber
    }
}
