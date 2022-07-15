package people.songpagu.goodgame.jpa.domain.token.repository

import org.springframework.data.jpa.repository.JpaRepository
import people.songpagu.goodgame.jpa.domain.token.entity.LoginTokenEntity

interface LoginTokenJpaRepository : JpaRepository<LoginTokenEntity, Long>
