package people.songpagu.goodgame.security.domain.member.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import people.songpagu.goodgame.domain.member.model.LoginMember
import people.songpagu.goodgame.domain.member.type.LoginType
import people.songpagu.goodgame.jpa.domain.member.entity.MemberEntity
import people.songpagu.goodgame.jpa.domain.member.entity.MemberLoginHistoryEntity
import people.songpagu.goodgame.jpa.domain.member.repository.MemberJpaRepository
import people.songpagu.goodgame.jpa.domain.member.repository.MemberLoginHistoryJpaRepository
import java.time.LocalDateTime
import java.util.UUID

@Service
class SecurityMemberService(
    private val memberRepository: MemberJpaRepository,
    private val memberLoginHistoryRepository: MemberLoginHistoryJpaRepository,
) {
    @Transactional
    fun signUpOrIn(email: String): LoginMember {
        val memberEntity: MemberEntity = memberRepository.findByMemberDetails(email) ?: signUp(email)
        val history = MemberLoginHistoryEntity(memberEntity = memberEntity)
        memberLoginHistoryRepository.save(history)

        return LoginMember(
            memberId = memberEntity.id!!,
            memberNumber = memberEntity.memberNumber,
            expireDatetime = LocalDateTime.now().plusDays(7),
            createdDateTime = LocalDateTime.now()
        )
    }

    private fun signUp(email: String): MemberEntity {
        return memberRepository.save(
            MemberEntity(
                memberNumber = UUID.randomUUID().toString(),
                memberDetails = email,
                loginType = LoginType.KAKAO
            )
        )
    }
}
