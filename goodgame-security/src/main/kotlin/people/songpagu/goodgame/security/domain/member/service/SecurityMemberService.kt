package people.songpagu.goodgame.security.domain.member.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import people.songpagu.goodgame.domain.member.model.LoginMember
import people.songpagu.goodgame.jpa.domain.member.entity.MemberEntity
import people.songpagu.goodgame.jpa.domain.member.entity.MemberLoginHistoryEntity
import people.songpagu.goodgame.jpa.domain.member.repository.MemberJpaRepository
import people.songpagu.goodgame.jpa.domain.member.repository.MemberLoginHistoryJpaRepository
import people.songpagu.goodgame.security.domain.member.service.Oauth2MemberExtractor.Oauth2ValidatedMember
import java.time.LocalDateTime
import java.util.UUID

@Service
class SecurityMemberService(
    private val memberRepository: MemberJpaRepository,
    private val memberLoginHistoryRepository: MemberLoginHistoryJpaRepository,
) {
    @Transactional
    fun signUpOrIn(oauth2Member: Oauth2ValidatedMember): LoginMember {
        val memberEntity: MemberEntity = memberRepository.findByAuthId(oauth2Member.authId) ?: signUp(oauth2Member)
        val history = MemberLoginHistoryEntity(memberEntity = memberEntity)
        memberLoginHistoryRepository.save(history)

        return LoginMember(
            memberNumber = memberEntity.memberNumber,
            expireDatetime = LocalDateTime.now().plusDays(LoginMember.EXPIRE_PERIOD),
            createdDateTime = LocalDateTime.now()
        )
    }

    private fun signUp(oauth2Member: Oauth2ValidatedMember): MemberEntity {
        return memberRepository.save(
            MemberEntity(
                memberNumber = UUID.randomUUID().toString().replace("-", ""),
                authId = oauth2Member.authId,
                loginType = oauth2Member.type,
                email = oauth2Member.email,
            )
        )
    }
}
