package people.songpagu.goodgame.jpa.domain.guild.facade

import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Repository
import people.songpagu.goodgame.application.guild.create.incoming.GuildCreateUseCase.GuildCreateCommand
import people.songpagu.goodgame.application.guild.create.outgoing.GuildCreatePort
import people.songpagu.goodgame.application.guild.find.outgoing.GuildListFindPort
import people.songpagu.goodgame.application.guild.find.outgoing.GuildListFindPort.GuildFindMoreQueryAnswerCollection
import people.songpagu.goodgame.application.guild.find.outgoing.GuildListFindPort.GuildFindQueryCondition
import people.songpagu.goodgame.jpa.domain.guild.entity.GuildEntity
import people.songpagu.goodgame.jpa.domain.guild.repository.GuildFindQueryDslRepository
import people.songpagu.goodgame.jpa.domain.guild.repository.GuildJpaRepository

@Repository
class GuildJpaFacade(
    private val guildJpaRepository: GuildJpaRepository,
    private val guildFindQueryDslRepository: GuildFindQueryDslRepository,
) : GuildCreatePort, GuildListFindPort {
    override fun create(guildCreateCommand: GuildCreateCommand) {
        val guildEntity: GuildEntity = GuildEntity.create(
            createMemberNumber = guildCreateCommand.createMemberNumber,
            guildName = guildCreateCommand.guildName
        )
        guildJpaRepository.save(guildEntity)
    }

    override fun findMoreBy(
        condition: GuildFindQueryCondition,
        pageNumber: Int,
        pageSize: Int,
    ): GuildFindMoreQueryAnswerCollection {
        return guildFindQueryDslRepository.findAllBy(condition, PageRequest.of(pageNumber, pageSize))
            .map { it.guildFindQueryAnswer }
            .let { GuildFindMoreQueryAnswerCollection(contents = it.content, totalPageSize = it.totalPages) }
    }

}
