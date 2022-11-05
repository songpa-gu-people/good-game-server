package people.songpagu.goodgame.jpa.lifecycle.guild

import people.songpagu.goodgame.domain.member.type.Gender
import people.songpagu.goodgame.jpa.domain.guild.entity.GUILD_NAME_MAX_LENGTH
import people.songpagu.goodgame.jpa.domain.guild.entity.GuildEntity
import people.songpagu.goodgame.jpa.domain.guild.repository.GuildJpaRepository
import people.songpagu.goodgame.jpa.domain.member.repository.MemberJpaRepository
import people.songpagu.goodgame.jpa.domain.member.repository.MemberPrivacyJpaRepository
import people.songpagu.goodgame.jpa.lifecycle.common.CArbitraries
import people.songpagu.goodgame.jpa.lifecycle.member.FixtureSavedMember

object FixtureSavedGuild {
    fun guild(
        memberJpaRepository: MemberJpaRepository,
        memberPrivacyJpaRepository: MemberPrivacyJpaRepository,
        guildJpaRepository: GuildJpaRepository,
    ): GuildEntity {
        return guildWithName(
            CArbitraries.strings(GUILD_NAME_MAX_LENGTH),
            memberJpaRepository,
            memberPrivacyJpaRepository,
            guildJpaRepository,
        )
    }

    fun guildWithName(
        guildName: String,
        memberJpaRepository: MemberJpaRepository,
        memberPrivacyJpaRepository: MemberPrivacyJpaRepository,
        guildJpaRepository: GuildJpaRepository,
    ): GuildEntity {
        val member1 = FixtureSavedMember.kakaoMember(
            memberJpaRepository = memberJpaRepository,
            memberPrivacyJpaRepository = memberPrivacyJpaRepository,
            gender = CArbitraries.randomFrom(Gender.values().toMutableList()),
            authId = CArbitraries.strings(),
        )
        return guildJpaRepository.save(
            GuildEntity.create(member1.memberNumber, guildName)
        )
    }
}