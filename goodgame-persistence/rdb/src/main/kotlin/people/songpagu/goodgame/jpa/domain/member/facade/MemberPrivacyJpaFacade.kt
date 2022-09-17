package people.songpagu.goodgame.jpa.domain.member.facade

import org.springframework.stereotype.Repository
import people.songpagu.goodgame.application.member.privacy.outgoing.MemberPrivacyFindPort
import people.songpagu.goodgame.domain.member.model.MemberPrivacy
import people.songpagu.goodgame.jpa.domain.member.entity.MemberPrivacyEntity
import people.songpagu.goodgame.jpa.domain.member.repository.MemberPrivacyJpaRepository

@Repository
class MemberPrivacyJpaFacade(
    private val memberPrivacyJpaRepository: MemberPrivacyJpaRepository
) : MemberPrivacyFindPort {
    override fun findBy(memberNumber: String): MemberPrivacy? {
        val memberPrivacyEntity: MemberPrivacyEntity? = memberPrivacyJpaRepository.findByMemberNumber(memberNumber)
        return memberPrivacyEntity?.let {
            MemberPrivacy(
                memberNumber = memberPrivacyEntity.memberNumber,
                email = memberPrivacyEntity.email,
                gender = memberPrivacyEntity.gender,
                memberPrivacyStatus = memberPrivacyEntity.status,
            )
        }
    }
}