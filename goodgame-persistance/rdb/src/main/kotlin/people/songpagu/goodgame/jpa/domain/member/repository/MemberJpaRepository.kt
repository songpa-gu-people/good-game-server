package people.songpagu.goodgame.jpa.domain.member.repository

import org.springframework.data.jpa.repository.JpaRepository
import people.songpagu.goodgame.jpa.domain.member.entity.MemberEntity

interface MemberJpaRepository : JpaRepository<MemberEntity, Long> {
    fun findByAuthId(authId: String): MemberEntity?
}
