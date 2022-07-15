package people.songpagu.goodgame.api.test

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestConstructor
import people.songpagu.goodgame.api.main.GoodGameApiApplication

@ActiveProfiles("test")
@SpringBootTest(classes = [GoodGameApiApplication::class])
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class GoodGameApiTest
