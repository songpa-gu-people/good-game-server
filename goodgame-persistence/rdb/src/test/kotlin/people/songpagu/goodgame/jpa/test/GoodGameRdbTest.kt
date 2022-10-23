package people.songpagu.goodgame.jpa.test

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestConstructor
import people.songpagu.goodgame.jpa.config.GoodGameJpaConfig
import people.songpagu.goodgame.jpa.lifecycle.TestJpaLifeCycleConfig

@EnableAutoConfiguration
@ActiveProfiles("test")
@SpringBootTest(classes = [GoodGameJpaConfig::class])
@Import(value = [TestJpaLifeCycleConfig::class])
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class GoodGameRdbTest
