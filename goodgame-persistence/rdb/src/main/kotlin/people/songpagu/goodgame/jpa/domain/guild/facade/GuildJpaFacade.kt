package people.songpagu.goodgame.jpa.domain.guild.facade

import org.springframework.stereotype.Repository
import people.songpagu.goodgame.application.guild.incoming.GuildCreateUseCase.GuildCreateCommand
import people.songpagu.goodgame.application.guild.outgoing.GuildCreatePort
import people.songpagu.goodgame.jpa.domain.guild.entity.GuildEntity
import people.songpagu.goodgame.jpa.domain.guild.repository.GuildJpaRepository

@Repository
class GuildJpaFacade(
    private val guildJpaRepository: GuildJpaRepository
) : GuildCreatePort {
    override fun create(command: GuildCreateCommand) {
        val guildEntity: GuildEntity = GuildEntity.create(
            createMemberNumber = command.createMemberNumber,
            command.guildName
        )
        guildJpaRepository.save(guildEntity)
    }
}