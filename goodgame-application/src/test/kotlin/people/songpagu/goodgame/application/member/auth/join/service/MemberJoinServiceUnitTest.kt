package people.songpagu.goodgame.application.member.auth.join.service

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe
import people.songpagu.goodgame.application.member.auth.join.incoming.MemberJoinUseCase
import people.songpagu.goodgame.application.member.auth.join.outgoing.LoginHistoryRecordPort
import people.songpagu.goodgame.application.member.auth.join.outgoing.MemberCreatePort
import people.songpagu.goodgame.application.member.auth.join.outgoing.MemberFindPort
import people.songpagu.goodgame.domain.member.model.LoginMember
import people.songpagu.goodgame.domain.member.type.Gender
import people.songpagu.goodgame.domain.member.type.LoginHistoryType
import people.songpagu.goodgame.domain.member.type.LoginType

internal class MemberJoinServiceUnitTest : BehaviorSpec({
    given("email, gender 입력이 되지 않으면") {
        val email = null
        val gender = null

        `when`("회원가입을 시도 할 때") {
            val service = MemberJoinService(
                memberFindPort = DO_NOT_THING_FIND_PORT,
                memberCreatePort = DO_NOT_THING_CREATE_PORT,
                loginHistoryRecordPort = DO_NOT_THING_RECORD_PORT,
            )

            val answer = service.join(
                command = MemberJoinUseCase.MemberJoinCommand(
                    authId = "authId",
                    loginType = LoginType.KAKAO,
                    email = email,
                    gender = gender
                )
            )

            then("실패한다") {
                answer shouldBe MemberJoinUseCase.MemberJoinAnswer.INCOMPLETE("서비스 이용을 위해 이메일과 성별을 입력해주세요.")
            }
        }
    }

    given("이미 회원가입이 되어 있으면") {
        val loginMember = LoginMember(memberNumber = "memberNumber")
        val memberFindPort = MemberFindPort { _, _ -> loginMember }

        `when`("회원가입 및 로그인 시도시") {
            val joinCommand = MemberJoinUseCase.MemberJoinCommand(
                authId = "authId",
                loginType = LoginType.KAKAO,
                email = "email",
                gender = Gender.MAN
            )

            val recordCapture = mutableSetOf<LoginHistoryRecordPort.LoginHistoryRecordCommand>()
            val service = MemberJoinService(
                memberFindPort = memberFindPort,
                memberCreatePort = DO_NOT_THING_CREATE_PORT,
                loginHistoryRecordPort = { recordCapture.add(it) },
            )

            val answer: MemberJoinUseCase.MemberJoinAnswer = service.join(joinCommand)

            then("로그인 된 것으로 기록하여 응답한다") {
                answer shouldBe MemberJoinUseCase.MemberJoinAnswer.JOINED(loginMember)
                recordCapture shouldContain LoginHistoryRecordPort.LoginHistoryRecordCommand(
                    memberNumber = loginMember.memberNumber,
                    type = LoginHistoryType.LOGIN
                )
            }
        }
    }

    given("회원가입한적이 없다면") {
        val loginMember: LoginMember? = null
        val memberFindPort = MemberFindPort { _, _ -> loginMember }

        `when`("회원가입 및 로그인 시도시") {
            val joinCommand = MemberJoinUseCase.MemberJoinCommand(
                authId = "authId",
                loginType = LoginType.KAKAO,
                email = "email",
                gender = Gender.MAN
            )

            val joinLoginMember = LoginMember(memberNumber = "memberNumber")

            val recordCapture = mutableSetOf<LoginHistoryRecordPort.LoginHistoryRecordCommand>()
            val service = MemberJoinService(
                memberFindPort = memberFindPort,
                memberCreatePort = { joinLoginMember },
                loginHistoryRecordPort = { recordCapture.add(it) },
            )

            val answer: MemberJoinUseCase.MemberJoinAnswer = service.join(joinCommand)

            then("회원가입 한 것으로 기록하여 응답한다") {
                answer shouldBe MemberJoinUseCase.MemberJoinAnswer.NEW(joinLoginMember)
                recordCapture shouldContain LoginHistoryRecordPort.LoginHistoryRecordCommand(
                    memberNumber = joinLoginMember.memberNumber,
                    type = LoginHistoryType.JOIN
                )
            }
        }
    }
}

) {
    companion object {
        val DO_NOT_THING_FIND_PORT: MemberFindPort = MemberFindPort { _, _ -> throw UnsupportedOperationException() }
        val DO_NOT_THING_CREATE_PORT: MemberCreatePort = MemberCreatePort { throw UnsupportedOperationException() }
        val DO_NOT_THING_RECORD_PORT: LoginHistoryRecordPort = LoginHistoryRecordPort { throw UnsupportedOperationException() }
    }
}
