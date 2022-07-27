package people.songpagu.goodgame.application.member.auth.join.service

import people.songpagu.goodgame.application.member.auth.join.incoming.MemberJoinUseCase
import people.songpagu.goodgame.application.member.auth.join.incoming.MemberJoinUseCase.MemberJoinAnswer.INCOMPLETE
import people.songpagu.goodgame.application.member.auth.join.incoming.MemberJoinUseCase.MemberJoinAnswer.JOINED
import people.songpagu.goodgame.application.member.auth.join.incoming.MemberJoinUseCase.MemberJoinAnswer.NEW
import people.songpagu.goodgame.application.member.auth.join.outgoing.LoginHistoryRecordPort
import people.songpagu.goodgame.application.member.auth.join.outgoing.MemberCreatePort
import people.songpagu.goodgame.application.member.auth.join.outgoing.MemberFindPort
import people.songpagu.goodgame.domain.exception.GoodGameCode.INTERNAL_SERVER_ERROR
import people.songpagu.goodgame.domain.member.model.LoginMember
import people.songpagu.goodgame.domain.member.type.LoginHistoryType
import java.util.UUID

class MemberJoinService(
    private val memberFindPort: MemberFindPort,
    private val memberCreatePort: MemberCreatePort,
    private val loginHistoryRecordPort: LoginHistoryRecordPort,
) : MemberJoinUseCase {

    override fun join(command: MemberJoinUseCase.MemberJoinCommand): MemberJoinUseCase.MemberJoinAnswer {
        if (command.notEnoughToJoin) {
            return INCOMPLETE("서비스 이용을 위해 이메일과 성별을 입력해주세요.")
        }

        val maybeLoginMember: LoginMember? = memberFindPort.findByAuthIdAndLoginType(authId = command.authId, loginType = command.loginType)

        val (loginMember: LoginMember, type: LoginHistoryType) = maybeLoginMember.joinIfNotPresent(command)

        val historyRecordCommand = LoginHistoryRecordPort.LoginHistoryRecordCommand(
            memberNumber = loginMember.memberNumber,
            type = type
        )
        loginHistoryRecordPort.record(historyRecordCommand)

        return when (type) {
            LoginHistoryType.JOIN -> NEW(loginMember)
            LoginHistoryType.LOGIN -> JOINED(loginMember)
            LoginHistoryType.LOGOUT -> INCOMPLETE(INTERNAL_SERVER_ERROR.exposureMessage)
        }
    }

    private fun LoginMember?.joinIfNotPresent(joinCommand: MemberJoinUseCase.MemberJoinCommand): Pair<LoginMember, LoginHistoryType> {
        if (this == null) {
            val command = MemberCreatePort.MemberCreateCommand(
                memberNumber = UUID.randomUUID().toString().replace("-", ""),
                authId = joinCommand.authId,
                loginType = joinCommand.loginType,
                email = requireNotNull(joinCommand.email),
                gender = requireNotNull(joinCommand.gender),
            )
            return memberCreatePort.create(command) to LoginHistoryType.JOIN
        }

        return this to LoginHistoryType.LOGIN
    }
}
