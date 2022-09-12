package people.songpagu.goodgame.jpa.lifecycle.member

import people.songpagu.goodgame.domain.member.type.LoginType
import people.songpagu.goodgame.jpa.domain.member.entity.MemberEntity
import people.songpagu.goodgame.jpa.domain.member.repository.MemberJpaRepository
import java.util.*

object FixtureSavedMember {
    fun kakaoMember(memberJpaRepository: MemberJpaRepository): MemberEntity {
        val memberNumber: String = UUID.randomUUID().toString().replace("-", "")
        val memberEntity = MemberEntity.create(memberNumber, "authId", LoginType.KAKAO)
        return memberJpaRepository.save(memberEntity)
    }
}