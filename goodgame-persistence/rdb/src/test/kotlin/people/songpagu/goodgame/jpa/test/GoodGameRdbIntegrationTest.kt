package people.songpagu.goodgame.jpa.test

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import people.songpagu.goodgame.jpa.domain.guild.repository.GuildJpaRepository
import people.songpagu.goodgame.jpa.domain.guild.repository.GuildMemberJpaRepository
import people.songpagu.goodgame.jpa.domain.member.repository.MemberJpaRepository
import people.songpagu.goodgame.jpa.domain.member.repository.MemberPrivacyJpaRepository
import people.songpagu.goodgame.jpa.lifecycle.TestJpaSweeper

@Suppress("SpringJavaInjectionPointsAutowiringInspection")
@GoodGameRdbTest
abstract class GoodGameRdbIntegrationTest {
    @Autowired
    private lateinit var testJpaSweeper: TestJpaSweeper

    @Autowired
    lateinit var memberJpaRepository: MemberJpaRepository

    @Autowired
    lateinit var memberPrivacyJpaRepository: MemberPrivacyJpaRepository

    @Autowired
    lateinit var guildMemberJpaRepository: GuildMemberJpaRepository

    @Autowired
    lateinit var guildJpaRepository: GuildJpaRepository

    @BeforeEach
    internal fun setUp() {
        testJpaSweeper.sweep()
        applySetUp()
    }

    protected fun applySetUp() {
    }

    @AfterEach
    internal fun tearDown() {
        applyTearDown()
    }

    protected fun applyTearDown() {
    }
}
