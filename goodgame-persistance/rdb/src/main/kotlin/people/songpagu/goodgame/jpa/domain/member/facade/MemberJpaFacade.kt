package people.songpagu.goodgame.jpa.domain.member.facade

import org.springframework.stereotype.Repository
import people.songpagu.goodgame.application.member.auth.join.outgoing.LoginHistoryRecordPort
import people.songpagu.goodgame.application.member.auth.join.outgoing.MemberCreatePort
import people.songpagu.goodgame.application.member.auth.join.outgoing.MemberFindPort
import people.songpagu.goodgame.domain.member.model.LoginMember
import people.songpagu.goodgame.domain.member.type.LoginType
import people.songpagu.goodgame.jpa.domain.member.entity.MemberEntity
import people.songpagu.goodgame.jpa.domain.member.entity.MemberLoginHistoryEntity
import people.songpagu.goodgame.jpa.domain.member.entity.MemberPrivacyEntity
import people.songpagu.goodgame.jpa.domain.member.repository.MemberJpaRepository
import people.songpagu.goodgame.jpa.domain.member.repository.MemberLoginHistoryJpaRepository
import people.songpagu.goodgame.jpa.domain.member.repository.MemberPrivacyJpaRepository

@Repository
class MemberJpaFacade(
    private val memberJpaRepository: MemberJpaRepository,
    private val memberPrivacyJpaRepository: MemberPrivacyJpaRepository,
    private val memberLoginHistoryJpaRepository: MemberLoginHistoryJpaRepository,
) : MemberCreatePort, MemberFindPort, LoginHistoryRecordPort {

    override fun create(command: MemberCreatePort.MemberCreateCommand): LoginMember {
        val memberEntity = memberJpaRepository.save(
            MemberEntity.create(
                memberNumber = command.memberNumber,
                authId = command.authId,
                loginType = command.loginType,
            )
        )

        memberPrivacyJpaRepository.save(
            MemberPrivacyEntity.create(
                memberEntity = memberEntity,
                email = command.email,
                gender = command.gender,
            )
        )

        return LoginMember(memberEntity.memberNumber)
    }

    override fun findByAuthIdAndLoginType(authId: String, loginType: LoginType): LoginMember? {
        val entity: MemberEntity? = memberJpaRepository.findByAuthIdAndLoginType(authId, loginType)

        return entity?.let {
            LoginMember(entity.memberNumber)
        }
    }

    override fun record(command: LoginHistoryRecordPort.LoginHistoryRecordCommand) {
        memberLoginHistoryJpaRepository.save(
            MemberLoginHistoryEntity(memberNumber = command.memberNumber, status = command.type)
        )
    }
}
