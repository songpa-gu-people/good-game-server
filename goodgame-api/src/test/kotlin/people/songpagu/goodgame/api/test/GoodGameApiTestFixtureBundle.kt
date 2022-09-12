package people.songpagu.goodgame.api.test

import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import people.songpagu.goodgame.application.token.generate.incoming.TokenGenerateUseCase
import people.songpagu.goodgame.jpa.domain.member.entity.MemberEntity
import people.songpagu.goodgame.jpa.domain.member.repository.MemberJpaRepository
import people.songpagu.goodgame.jpa.lifecycle.member.FixtureSavedMember

abstract class GoodGameApiTestFixtureBundle : GoodGameApiRestAssured() {
    @Suppress("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    protected lateinit var memberJpaRepository: MemberJpaRepository

    @Suppress("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    protected lateinit var tokenGenerateUseCase: TokenGenerateUseCase

    protected fun initKakaoMember(): MemberEntity {
        return FixtureSavedMember.kakaoMember(memberJpaRepository)
    }

    protected fun createToken(memberEntity: MemberEntity): String {
        return tokenGenerateUseCase.issue(TokenGenerateUseCase.TokenIssueCommand.accessToken(memberEntity.memberNumber))
    }
}
