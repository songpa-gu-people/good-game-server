package people.songpagu.goodgame.jpa.lifecycle

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(value = [TestJpaSweeper::class])
class TestJpaLifeCycleConfig
