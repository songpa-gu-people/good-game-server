package people.songpagu.goodgame.jpa.domain.guild.repository

import org.springframework.data.jpa.repository.JpaRepository
import people.songpagu.goodgame.jpa.domain.guild.entity.GuildMemberEntity
import people.songpagu.goodgame.jpa.domain.guild.entity.GuildMemberEntityId

interface GuildMemberJpaRepository : JpaRepository<GuildMemberEntity, GuildMemberEntityId> {
}