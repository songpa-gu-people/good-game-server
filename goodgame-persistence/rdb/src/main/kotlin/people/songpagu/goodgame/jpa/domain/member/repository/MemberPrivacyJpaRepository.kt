package people.songpagu.goodgame.jpa.domain.member.repository

import org.springframework.data.jpa.repository.JpaRepository
import people.songpagu.goodgame.jpa.domain.member.entity.MemberPrivacyEntity

interface MemberPrivacyJpaRepository : JpaRepository<MemberPrivacyEntity, Long> {
    fun findByMemberNumber(memberNumber: String): MemberPrivacyEntity?
}
