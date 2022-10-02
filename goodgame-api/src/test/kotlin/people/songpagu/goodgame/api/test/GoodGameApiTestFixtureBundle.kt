package people.songpagu.goodgame.api.test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.TransactionStatus
import org.springframework.transaction.support.DefaultTransactionDefinition
import people.songpagu.goodgame.application.token.generate.incoming.TokenGenerateUseCase
import people.songpagu.goodgame.domain.member.type.Gender
import people.songpagu.goodgame.jpa.domain.guild.repository.GuildJpaRepository
import people.songpagu.goodgame.jpa.domain.member.entity.MemberEntity
import people.songpagu.goodgame.jpa.domain.member.repository.MemberJpaRepository
import people.songpagu.goodgame.jpa.domain.member.repository.MemberPrivacyJpaRepository
import people.songpagu.goodgame.jpa.lifecycle.member.FixtureSavedMember

abstract class GoodGameApiTestFixtureBundle : GoodGameApiRestAssured() {
    @Suppress("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    protected lateinit var transactionManager: PlatformTransactionManager;

    @Suppress("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    protected lateinit var memberPrivacyJpaRepository: MemberPrivacyJpaRepository

    @Suppress("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    protected lateinit var memberJpaRepository: MemberJpaRepository

    @Suppress("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    protected lateinit var tokenGenerateUseCase: TokenGenerateUseCase

    @Suppress("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    protected lateinit var guildJpaRepository: GuildJpaRepository


    protected fun initKakaoMember(
        gender: Gender = Gender.MAN
    ): MemberEntity {
        return FixtureSavedMember.kakaoMember(memberJpaRepository, memberPrivacyJpaRepository, gender)
    }

    protected fun createToken(memberEntity: MemberEntity): String {
        return tokenGenerateUseCase.issue(TokenGenerateUseCase.TokenIssueCommand.accessToken(memberEntity.memberNumber))
    }

    protected fun getTransaction(): TransactionStatus {
        return transactionManager.getTransaction(DefaultTransactionDefinition())
    }
}
