package people.songpagu.goodgame.security.test

import org.junit.jupiter.api.Tag
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestConstructor
import people.songpagu.goodgame.jpa.lifecycle.TestJpaLifeCycleConfig
import people.songpagu.goodgame.security.config.GoodGameSecurityConfig


@Tag("integration")
@ActiveProfiles("test")
@EnableAutoConfiguration
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Import(value = [TestJpaLifeCycleConfig::class])
@SpringBootTest(classes = [GoodGameSecurityConfig::class])
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
annotation class SecuritySpringTestContext
