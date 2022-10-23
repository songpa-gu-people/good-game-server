package people.songpagu.goodgame.jpa.domain.guild.repository

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import people.songpagu.goodgame.domain.guild.type.GuildMemberRole
import people.songpagu.goodgame.domain.member.type.Gender
import people.songpagu.goodgame.jpa.domain.guild.entity.GuildEntity
import people.songpagu.goodgame.jpa.domain.guild.entity.GuildMemberEntity
import people.songpagu.goodgame.jpa.domain.guild.repository.dto.GuildFindRow
import people.songpagu.goodgame.jpa.lifecycle.member.FixtureSavedMember
import people.songpagu.goodgame.jpa.test.GoodGameRdbIntegrationTest

class GuildFindMoreQueryDslRepositoryTest(
    private val sut: GuildFindQueryDslRepository,
) : GoodGameRdbIntegrationTest() {

    @DisplayName("조회 결과가 없는 경우")
    @Test
    fun test1() {
        //given
        val startId: Long? = null
        val size = 10L

        //when
        val more: List<GuildFindRow> = sut.findMoreBy(startId, size)

        //then
        assertThat(more).hasSize(0)
    }

    @DisplayName("조회 결과가 1개인 경우")
    @Test
    fun test2() {
        //given
        val member1 = FixtureSavedMember.kakaoMember(memberJpaRepository, memberPrivacyJpaRepository, Gender.WOMAN)
        guildJpaRepository.save(GuildEntity.create(member1.memberNumber, "GUILD_NAME"))

        //when
        val more: List<GuildFindRow> = sut.findMoreBy(null, 10)

        //then
        assertThat(more).hasSize(1)
    }

    @DisplayName("조회 결과를 벗어난 검색 요청")
    @Test
    fun test2_1() {
        //given
        val member1 = FixtureSavedMember.kakaoMember(memberJpaRepository, memberPrivacyJpaRepository, Gender.WOMAN)
        val guild1 = guildJpaRepository.save(GuildEntity.create(member1.memberNumber, "GUILD_NAME"))

        //when
        val more: List<GuildFindRow> = sut.findMoreBy(guild1.id, 10)

        //then
        assertThat(more).hasSize(0)
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

        //when
        val more = sut.findMoreBy(null, 10)

        //then
        assertThat(more).hasSize(2)
    }

    @DisplayName("조회 결과가 n개중 부분 검색한 경우")
    @Test
    fun test3_1() {
        //given
        val member1 = FixtureSavedMember.kakaoMember(
            memberJpaRepository = memberJpaRepository,
            memberPrivacyJpaRepository = memberPrivacyJpaRepository,
            gender = Gender.WOMAN,
            authId = "auth1",
        )
        val guild1 = guildJpaRepository.save(GuildEntity.create(member1.memberNumber, "GUILD_NAME_1"))

        val member2 = FixtureSavedMember.kakaoMember(
            memberJpaRepository = memberJpaRepository,
            memberPrivacyJpaRepository = memberPrivacyJpaRepository,
            gender = Gender.WOMAN,
            authId = "auth2",
        )
        val guild2 = guildJpaRepository.save(GuildEntity.create(member2.memberNumber, "GUILD_NAME_2"))

        //when
        val more = sut.findMoreBy(guild2.id, 10)

        //then
        assertThat(more).hasSize(1)
        assertThat(more[0]._guildId).isEqualTo(guild1.id)
    }

    @DisplayName("회원 수 정상 조회 확인")
    @Test
    fun test4() {
        //given
        val member1 = FixtureSavedMember.kakaoMember(
            memberJpaRepository = memberJpaRepository,
            memberPrivacyJpaRepository = memberPrivacyJpaRepository,
            gender = Gender.WOMAN,
            authId = "auth1",
        )
        val guild1 = guildJpaRepository.save(GuildEntity.create(member1.memberNumber, "GUILD_NAME_1"))

        val member2 = FixtureSavedMember.kakaoMember(
            memberJpaRepository = memberJpaRepository,
            memberPrivacyJpaRepository = memberPrivacyJpaRepository,
            gender = Gender.WOMAN,
            authId = "auth2",
        )
        val member3 = FixtureSavedMember.kakaoMember(
            memberJpaRepository = memberJpaRepository,
            memberPrivacyJpaRepository = memberPrivacyJpaRepository,
            gender = Gender.WOMAN,
            authId = "auth3",
        )
        val guild2 = guildJpaRepository.save(GuildEntity.create(member2.memberNumber, "GUILD_NAME_2"))
        guildMemberJpaRepository.save(GuildMemberEntity(member3.memberNumber, guild2, GuildMemberRole.MEMBER))

        //when
        val more = sut.findMoreBy(null, 10)

        //then
        assertThat(more).hasSize(2)
        assertThat(more[0]._guildName).isEqualTo(guild2.guildName)
        assertThat(more[0]._guildMemberSize).isEqualTo(2)

        assertThat(more[1]._guildName).isEqualTo(guild1.guildName)
        assertThat(more[1]._guildMemberSize).isEqualTo(1)
    }
}
