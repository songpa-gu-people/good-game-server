package people.songpagu.goodgame.jpa.domain.matching.repository

import org.springframework.data.jpa.repository.JpaRepository
import people.songpagu.goodgame.jpa.domain.matching.entity.MatchingOptionEntity

interface MatchingOptionJpaRepository : JpaRepository<MatchingOptionEntity, Long> {
    fun findByMemberNumber(memberNumber: String): MatchingOptionEntity?
}