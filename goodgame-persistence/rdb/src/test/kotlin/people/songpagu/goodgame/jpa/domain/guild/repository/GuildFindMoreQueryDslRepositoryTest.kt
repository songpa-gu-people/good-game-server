package people.songpagu.goodgame.jpa.domain.guild.repository

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import people.songpagu.goodgame.application.guild.find.outgoing.GuildListFindPort
import people.songpagu.goodgame.domain.guild.type.GuildMemberRole
import people.songpagu.goodgame.domain.member.type.Gender
import people.songpagu.goodgame.jpa.domain.guild.entity.GuildMemberEntity
import people.songpagu.goodgame.jpa.domain.guild.repository.dto.GuildFindRow
import people.songpagu.goodgame.jpa.lifecycle.guild.FixtureSavedGuild
import people.songpagu.goodgame.jpa.lifecycle.member.FixtureSavedMember
import people.songpagu.goodgame.jpa.test.GoodGameRdbIntegrationTest

@DisplayName("기본 길드 더보기 검색")
class GuildFindMoreQueryDslRepositoryTest(
    private val sut: GuildFindQueryDslRepository,
) : GoodGameRdbIntegrationTest() {

    private val emptyCondition = GuildListFindPort.GuildFindQueryCondition(name = null)

    @DisplayName("조회 결과가 없는 경우")
    @Test
    fun test1() {
        //given
        val size = 10

        //when
        val more: Page<GuildFindRow> = sut.findAllBy(emptyCondition, PageRequest.of(0, size))

        //then
        assertThat(more).hasSize(0)
    }

    @DisplayName("조회 결과가 1개인 경우")
    @Test
    fun test2() {
        //given
        val guild1 = FixtureSavedGuild.guild(memberJpaRepository, memberPrivacyJpaRepository, guildJpaRepository)

        //when
        val more: Page<GuildFindRow> = sut.findAllBy(emptyCondition, PageRequest.of(0, 10))

        //then
        assertThat(more).hasSize(1)
    }

    @DisplayName("조회 결과가 n개인 경우")
    @Test
    fun test3() {
        //given
        val guild1 = FixtureSavedGuild.guild(memberJpaRepository, memberPrivacyJpaRepository, guildJpaRepository)
        val guild2 = FixtureSavedGuild.guild(memberJpaRepository, memberPrivacyJpaRepository, guildJpaRepository)

        //when
        val more: Page<GuildFindRow> = sut.findAllBy(emptyCondition, PageRequest.of(0, 10))


        //then
        assertThat(more).hasSize(2)
    }

    @DisplayName("2페이지 조회")
    @Test
    fun test3_1() {
        //given
        val guild1 = FixtureSavedGuild.guild(memberJpaRepository, memberPrivacyJpaRepository, guildJpaRepository)
        val guild2 = FixtureSavedGuild.guild(memberJpaRepository, memberPrivacyJpaRepository, guildJpaRepository)

        //when
        val more: Page<GuildFindRow> = sut.findAllBy(emptyCondition, PageRequest.of(1, 1))


        //then
        assertThat(more).hasSize(1)
        assertThat(more.content[0]._guildId).isEqualTo(guild1.id)
    }

    @DisplayName("회원 수 정상 조회 확인")
    @Test
    fun test4() {
        //given
        val guild1 = FixtureSavedGuild.guild(memberJpaRepository, memberPrivacyJpaRepository, guildJpaRepository)

        val guild2 = FixtureSavedGuild.guild(memberJpaRepository, memberPrivacyJpaRepository, guildJpaRepository)
        val member3 = FixtureSavedMember.kakaoMember(
            memberJpaRepository = memberJpaRepository,
            memberPrivacyJpaRepository = memberPrivacyJpaRepository,
            gender = Gender.WOMAN,
            authId = "auth3",
        )
        guildMemberJpaRepository.save(GuildMemberEntity(member3.memberNumber, guild2, GuildMemberRole.MEMBER))

        //when
        val more: Page<GuildFindRow> = sut.findAllBy(emptyCondition, PageRequest.of(0, 10))

        //then
        assertThat(more).hasSize(2)
        assertThat(more.content[0]._guildName).isEqualTo(guild2.guildName)
        assertThat(more.content[0]._guildMemberSize).isEqualTo(2)

        assertThat(more.content[1]._guildName).isEqualTo(guild1.guildName)
        assertThat(more.content[1]._guildMemberSize).isEqualTo(1)
    }
}
