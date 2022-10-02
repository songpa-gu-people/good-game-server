package people.songpagu.goodgame.jpa.domain.guild.repository

import org.springframework.data.jpa.repository.JpaRepository
import people.songpagu.goodgame.jpa.domain.guild.entity.GuildEntity

interface GuildJpaRepository : JpaRepository<GuildEntity, Long> {
}