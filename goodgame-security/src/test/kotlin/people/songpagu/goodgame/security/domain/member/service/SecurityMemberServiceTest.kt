package people.songpagu.goodgame.security.domain.member.service

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import people.songpagu.goodgame.domain.exception.GoodGameException
import people.songpagu.goodgame.domain.member.model.LoginMember
import people.songpagu.goodgame.domain.member.type.LoginHistoryType
import people.songpagu.goodgame.domain.member.type.LoginType
import people.songpagu.goodgame.jpa.domain.member.entity.MemberEntity
import people.songpagu.goodgame.jpa.domain.member.entity.MemberLoginHistoryEntity
import people.songpagu.goodgame.jpa.domain.member.entity.MemberPrivacyEntity
import people.songpagu.goodgame.jpa.domain.member.repository.MemberJpaRepository
import people.songpagu.goodgame.jpa.domain.member.repository.MemberLoginHistoryJpaRepository
import people.songpagu.goodgame.jpa.domain.member.repository.MemberPrivacyJpaRepository
import people.songpagu.goodgame.security.test.SecuritySpringTest

internal class SecurityMemberServiceTest(
    private val sut: SecurityMemberService,

    private val memberJpaRepository: MemberJpaRepository,
    private val memberPrivacyJpaRepository: MemberPrivacyJpaRepository,
    private val memberLoginHistoryJpaRepository: MemberLoginHistoryJpaRepository,
) : SecuritySpringTest() {

    @DisplayName("새로운 회원가입 시 성공 응답")
    @Test
    fun test1() {
        //given
        val command = Oauth2MemberExtractor.Oauth2ValidatedMember.Kakao(authId = "authId", email = "email", gender = "male")

        //when
        val answer: LoginMember = sut.signUpOrIn(command)

        //then
        val memberEntity = memberJpaRepository.findByAuthIdAndLoginType(command.authId, LoginType.KAKAO)
        val savedMemberNumber = requireNotNull(memberEntity).memberNumber
        assertThat(answer.memberNumber).isEqualTo(savedMemberNumber)

        val memberPrivacyCollection: List<MemberPrivacyEntity> = memberPrivacyJpaRepository.findAll()
        assertThat(memberPrivacyCollection).hasSize(1)

        val memberLoginHistories: List<MemberLoginHistoryEntity> = memberLoginHistoryJpaRepository.findAll()
        assertThat(memberLoginHistories[0].status).isEqualTo(LoginHistoryType.JOIN)
    }

    @DisplayName("이미 가입된 회원의 로그인 시 성공 응답")
    @Test
    fun test2() {
        //given
        val authId = "authId"
        val memberNumber = "memberNumber"
        memberJpaRepository.save(MemberEntity.create(memberNumber = memberNumber, authId = authId, loginType = LoginType.KAKAO))

        val command = Oauth2MemberExtractor.Oauth2ValidatedMember.Kakao(authId = authId, email = "email", gender = "male")

        //when
        val answer: LoginMember = sut.signUpOrIn(command)

        //then
        val memberEntity = memberJpaRepository.findByAuthIdAndLoginType(command.authId, LoginType.KAKAO)
        val savedMemberNumber = requireNotNull(memberEntity).memberNumber
        assertThat(answer.memberNumber).isEqualTo(savedMemberNumber)

        val memberLoginHistories: List<MemberLoginHistoryEntity> = memberLoginHistoryJpaRepository.findAll()
        assertThat(memberLoginHistories).hasSize(1)
        assertThat(memberLoginHistories[0].status).isEqualTo(LoginHistoryType.LOGIN)
    }

    @DisplayName("필수 동의 항목이 없는 경우 실패 응답")
    @Test
    fun test3() {
        //given
        val command = Oauth2MemberExtractor.Oauth2ValidatedMember.Kakao(authId = "authId", email = null, gender = null)

        //when
        val goodGameException = assertThrows<GoodGameException> { sut.signUpOrIn(command) }

        //then
        assertThat(goodGameException).hasMessage("서비스 이용을 위해 이메일과 성별을 입력해주세요.")
    }
}
