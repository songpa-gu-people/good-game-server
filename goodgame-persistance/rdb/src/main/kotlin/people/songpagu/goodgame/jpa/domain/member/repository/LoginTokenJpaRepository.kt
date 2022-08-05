package people.songpagu.goodgame.jpa.domain.member.repository

import org.springframework.data.jpa.repository.JpaRepository
import people.songpagu.goodgame.jpa.domain.member.entity.LoginTokenEntity

interface LoginTokenJpaRepository : JpaRepository<LoginTokenEntity, Long> {
    fun findAllByMemberNumber(memberNumber: String): List<LoginTokenEntity>

    fun findBySubject(subject: String): LoginTokenEntity
}
