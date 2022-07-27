package people.songpagu.goodgame.security.domain.member.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import people.songpagu.goodgame.application.member.auth.join.incoming.MemberJoinUseCase
import people.songpagu.goodgame.domain.member.model.LoginMember
import people.songpagu.goodgame.security.domain.member.exception.GoodGameLoginException
import people.songpagu.goodgame.security.domain.member.service.Oauth2MemberExtractor.Oauth2ValidatedMember
import people.songpagu.infrastructure.log.Slf4j
import people.songpagu.infrastructure.log.Slf4j.Companion.log

@Slf4j
@Service
class SecurityMemberService(
    private val memberJoinUseCase: MemberJoinUseCase,
) {
    @Transactional
    fun signUpOrIn(oauth2Member: Oauth2ValidatedMember): LoginMember {
        val command: MemberJoinUseCase.MemberJoinCommand = oauth2Member.toMemberJoinCommand()

        val answer: MemberJoinUseCase.MemberJoinAnswer = memberJoinUseCase.join(command)
        log.info("[로그인] : {}", answer)

        if (answer is MemberJoinUseCase.MemberJoinAnswer.INCOMPLETE) {
            throw GoodGameLoginException(exposureMessage = answer.message)
        }

        return answer.loginMember
    }
}
