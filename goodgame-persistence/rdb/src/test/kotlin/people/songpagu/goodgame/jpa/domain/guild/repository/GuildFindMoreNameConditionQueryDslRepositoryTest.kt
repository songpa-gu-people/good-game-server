package people.songpagu.goodgame.jpa.domain.guild.repository

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import people.songpagu.goodgame.application.guild.find.outgoing.GuildFindMorePort
import people.songpagu.goodgame.domain.member.type.Gender
import people.songpagu.goodgame.jpa.domain.guild.entity.GuildEntity
import people.songpagu.goodgame.jpa.domain.guild.repository.dto.GuildFindRow
import people.songpagu.goodgame.jpa.lifecycle.member.FixtureSavedMember
import people.songpagu.goodgame.jpa.test.GoodGameRdbIntegrationTest

@DisplayName("이름을 조건으로 길드를 검색한 경우")
class GuildFindMoreNameConditionQueryDslRepositoryTest(
    private val sut: GuildFindQueryDslRepository,
) : GoodGameRdbIntegrationTest() {

    @DisplayName("조회 결과가 없는 경우")
    @Test
    fun test1() {
        //given
        val size = 10L
        val guildName = ""
        val condition = GuildFindMorePort.GuildFindQueryCondition(name = guildName)

        //when
        val more: List<GuildFindRow> = sut.findMoreBy(
            startId = null,
            condition = condition,
            size = size,
        )

        //then
        assertThat(more).hasSize(0)
    }

    @DisplayName("조회 결과가 1개인 경우")
    @Test
    fun test2() {
        //given
        val member1 = FixtureSavedMember.kakaoMember(memberJpaRepository, memberPrivacyJpaRepository, Gender.WOMAN)
        val guildEntity: GuildEntity = guildJpaRepository.save(GuildEntity.create(member1.memberNumber, "GUILD_NAME"))
        val condition = GuildFindMorePort.GuildFindQueryCondition(name = guildEntity.guildName)

        //when
        val more: List<GuildFindRow> = sut.findMoreBy(
            startId = null,
            condition = condition,
            size = 10L,
        )

        //then
        assertThat(more).hasSize(1)
    }

    @DisplayName("조회 결과가 n개인 경우")
    @Test
    fun test3() {
        //given
        val member1 = FixtureSavedMember.kakaoMember(
            memberJpaRepository = memberJpaRepository,
            memberPrivacyJpaRepository = memberPrivacyJpaRepository,
            gender = Gender.WOMAN,
            authId = "auth1",
        )
        guildJpaRepository.save(GuildEntity.create(member1.memberNumber, "GUILD_NAME_1"))

        val member2 = FixtureSavedMember.kakaoMember(
            memberJpaRepository = memberJpaRepository,
            memberPrivacyJpaRepository = memberPrivacyJpaRepository,
            gender = Gender.WOMAN,
            authId = "auth2",
        )
        guildJpaRepository.save(GuildEntity.create(member2.memberNumber, "GUILD_NAME_2"))

        val condition = GuildFindMorePort.GuildFindQueryCondition(name = "GUILD")

        //when
        val more: List<GuildFindRow> = sut.findMoreBy(
            startId = null,
            condition = condition,
            size = 10L,
        )

        //then
        assertThat(more).hasSize(2)
    }

    @DisplayName("중간 검색어로 검색한 경우")
    @Test
    fun test4() {
        //given
        val member1 = FixtureSavedMember.kakaoMember(
            memberJpaRepository = memberJpaRepository,
            memberPrivacyJpaRepository = memberPrivacyJpaRepository,
            gender = Gender.WOMAN,
            authId = "auth1",
        )
        guildJpaRepository.save(GuildEntity.create(member1.memberNumber, "GUILD_NAME_1"))

        val member2 = FixtureSavedMember.kakaoMember(
            memberJpaRepository = memberJpaRepository,
            memberPrivacyJpaRepository = memberPrivacyJpaRepository,
            gender = Gender.WOMAN,
            authId = "auth2",
        )
        guildJpaRepository.save(GuildEntity.create(member2.memberNumber, "GUILD_NAME_2"))

        val condition = GuildFindMorePort.GuildFindQueryCondition(name = "NAME")

        //when
        val more: List<GuildFindRow> = sut.findMoreBy(
            startId = null,
            condition = condition,
            size = 10L,
        )

        //then
        assertThat(more).hasSize(2)
    }
}
