package people.songpagu.goodgame.jpa.domain.guild.facade

import org.springframework.stereotype.Repository
import people.songpagu.goodgame.application.guild.create.incoming.GuildCreateUseCase.GuildCreateCommand
import people.songpagu.goodgame.application.guild.create.outgoing.GuildCreatePort
import people.songpagu.goodgame.application.guild.find.outgoing.GuildFindMorePort
import people.songpagu.goodgame.jpa.domain.guild.entity.GuildEntity
import people.songpagu.goodgame.jpa.domain.guild.repository.GuildFindMoreQueryDslRepository
import people.songpagu.goodgame.jpa.domain.guild.repository.GuildJpaRepository

@Repository
class GuildJpaFacade(
    private val guildJpaRepository: GuildJpaRepository,
    private val guildFindMoreQueryDslRepository: GuildFindMoreQueryDslRepository,
) : GuildCreatePort, GuildFindMorePort {
    override fun create(guildCreateCommand: GuildCreateCommand) {
        val guildEntity: GuildEntity = GuildEntity.create(
            createMemberNumber = guildCreateCommand.createMemberNumber,
            guildName = guildCreateCommand.guildName
        )
        guildJpaRepository.save(guildEntity)
    }

    override fun findMoreBy(startId: Long?, size: Long): GuildFindMorePort.GuildFindMoreQueryAnswerCollection {
        return guildFindMoreQueryDslRepository.findMoreBy(startId = startId, size = size)
            .map { it.guildFindMoreQueryAnswer }
            .let { GuildFindMorePort.GuildFindMoreQueryAnswerCollection(it) }
    }
}
