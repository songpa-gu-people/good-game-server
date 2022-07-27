package people.songpagu.goodgame.security.test

import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import people.songpagu.goodgame.jpa.lifecycle.TestJpaSweeper

@SecuritySpringTestContext
abstract class SecuritySpringTest {
    @Suppress("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private lateinit var testJpaSweeper: TestJpaSweeper

    @BeforeEach
    internal fun setUp() {
        beforeSweep()
        testJpaSweeper.sweep()
        afterSweep()
    }

    protected fun beforeSweep() {}

    protected fun afterSweep() {}
}
