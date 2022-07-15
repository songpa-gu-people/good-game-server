package people.songpagu.goodgame.jpa.domain.member.repository

import org.springframework.data.jpa.repository.JpaRepository
import people.songpagu.goodgame.jpa.domain.member.entity.MemberEntity
import people.songpagu.goodgame.jpa.domain.member.entity.MemberLoginHistoryEntity

interface MemberLoginHistoryJpaRepository : JpaRepository<MemberLoginHistoryEntity, Long>
