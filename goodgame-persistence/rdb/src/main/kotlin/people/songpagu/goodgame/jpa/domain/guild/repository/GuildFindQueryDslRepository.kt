package people.songpagu.goodgame.jpa.domain.guild.repository

import com.querydsl.core.types.Projections
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.core.types.dsl.StringPath
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository
import people.songpagu.goodgame.application.guild.find.outgoing.GuildListFindPort.GuildFindQueryCondition
import people.songpagu.goodgame.domain.guild.model.Guild
import people.songpagu.goodgame.jpa.domain.guild.entity.QGuildEntity.guildEntity
import people.songpagu.goodgame.jpa.domain.guild.entity.QGuildMemberEntity.guildMemberEntity
import people.songpagu.goodgame.jpa.domain.guild.repository.dto.GuildFindRow

@Repository
class GuildFindQueryDslRepository : QuerydslRepositorySupport(Guild::class.java) {
    fun findAllBy(
        condition: GuildFindQueryCondition,
        pageable: Pageable,
    ): Page<GuildFindRow> {
        val fetchResults = requireNotNull(querydsl).createQuery(guildEntity)
            .select(
                Projections.fields(
                    GuildFindRow::class.java,
                    guildEntity.id.`as`("_guildId"),
                    guildEntity.guildNumber.`as`("_guildNumber"),
                    guildMemberEntity.count().`as`("_guildMemberSize"),
                    guildEntity.guildName.`as`("_guildName"),
                )
            )
            .from(guildEntity)
            .leftJoin(guildEntity.guildMembers, guildMemberEntity).on(guildEntity.eq(guildMemberEntity.guildEntity))
            .where(
                guildEntity.guildName.containOrNull(condition.name)
            )
            .orderBy(guildEntity.id.desc())
            .groupBy(guildEntity)
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .fetchResults()

        return PageImpl(fetchResults.results, pageable, fetchResults.total)
    }

    private fun StringPath.containOrNull(query: String?): BooleanExpression? {
        return query?.let { this.contains(it) }
    }
}
