package people.songpagu.goodgame.jpa.lifecycle.member

import people.songpagu.goodgame.domain.member.type.Gender
import people.songpagu.goodgame.domain.member.type.LoginType
import people.songpagu.goodgame.jpa.domain.member.entity.MemberEntity
import people.songpagu.goodgame.jpa.domain.member.entity.MemberPrivacyEntity
import people.songpagu.goodgame.jpa.domain.member.repository.MemberJpaRepository
import people.songpagu.goodgame.jpa.domain.member.repository.MemberPrivacyJpaRepository
import java.util.*

object FixtureSavedMember {
    fun kakaoMember(
        memberJpaRepository: MemberJpaRepository,
        memberPrivacyJpaRepository: MemberPrivacyJpaRepository,
        gender: Gender
    ): MemberEntity {
        val memberNumber: String = UUID.randomUUID().toString().replace("-", "")
        val memberEntity = MemberEntity.create(memberNumber, "authId", LoginType.KAKAO)
        memberPrivacyJpaRepository.save(MemberPrivacyEntity.create(memberEntity, "email@email.co", gender))
        return memberJpaRepository.save(memberEntity)
    }
}