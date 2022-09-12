package people.songpagu.goodgame.api.test

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestConstructor
import people.songpagu.goodgame.api.main.GoodGameApiApplication
import people.songpagu.goodgame.jpa.lifecycle.TestJpaLifeCycleConfig

@ActiveProfiles("test")
@SpringBootTest(classes = [GoodGameApiApplication::class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@Target(AnnotationTarget.CLASS)
@Import(value = [TestJpaLifeCycleConfig::class])
@Retention(AnnotationRetention.RUNTIME)
annotation class GoodGameApiTestContext
