package people.songpagu.goodgame.jpa.domain.guild.repository

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import people.songpagu.goodgame.application.guild.find.outgoing.GuildListFindPort
import people.songpagu.goodgame.jpa.domain.guild.repository.dto.GuildFindRow
import people.songpagu.goodgame.jpa.lifecycle.guild.FixtureSavedGuild
import people.songpagu.goodgame.jpa.test.GoodGameRdbIntegrationTest

@DisplayName("이름을 조건으로 길드를 검색한 경우")
class GuildFindMoreNameConditionQueryDslRepositoryTest(
    private val sut: GuildFindQueryDslRepository,
) : GoodGameRdbIntegrationTest() {

    @DisplayName("조회 결과가 없는 경우")
    @Test
    fun test1() {
        //given
        val size = 10
        val guildName = ""
        val condition = GuildListFindPort.GuildFindQueryCondition(name = guildName)

        //when
        val more: Page<GuildFindRow> = sut.findAllBy(
            condition = condition,
            pageable = PageRequest.of(0, size)
        )

        //then
        assertThat(more).hasSize(0)
    }

    @DisplayName("조회 결과가 1개인 경우")
    @Test
    fun test2() {
        //given
        val guildEntity = FixtureSavedGuild.guild(memberJpaRepository, memberPrivacyJpaRepository, guildJpaRepository)
        val condition = GuildListFindPort.GuildFindQueryCondition(name = guildEntity.guildName)

        //when
        val more: Page<GuildFindRow> = sut.findAllBy(
            condition = condition,
            pageable = PageRequest.of(0, 10)
        )

        //then
        assertThat(more).hasSize(1)
    }

    @DisplayName("조회 결과가 n개인 경우")
    @Test
    fun test3() {
        //given
        FixtureSavedGuild.guildWithName("GUILD_NAME_1", memberJpaRepository, memberPrivacyJpaRepository, guildJpaRepository)
        FixtureSavedGuild.guildWithName("GUILD_NAME_2", memberJpaRepository, memberPrivacyJpaRepository, guildJpaRepository)

        val condition = GuildListFindPort.GuildFindQueryCondition(name = "GUILD")

        //when
        val more: Page<GuildFindRow> = sut.findAllBy(
            condition = condition,
            pageable = PageRequest.of(0, 10)
        )

        //then
        assertThat(more).hasSize(2)
    }

    @DisplayName("중간 검색어로 검색한 경우")
    @Test
    fun test4() {
        //given
        FixtureSavedGuild.guildWithName("GUILD_NAME_1", memberJpaRepository, memberPrivacyJpaRepository, guildJpaRepository)
        FixtureSavedGuild.guildWithName("GUILD_NAME_2", memberJpaRepository, memberPrivacyJpaRepository, guildJpaRepository)

        val condition = GuildListFindPort.GuildFindQueryCondition(name = "NAME")

        //when
        val more: Page<GuildFindRow> = sut.findAllBy(
            condition = condition,
            pageable = PageRequest.of(0, 10)
        )

        //then
        assertThat(more).hasSize(2)
    }
}
