package people.songpagu.infrastructure.jwt.jjwt

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import people.songpagu.goodgame.application.token.generate.incoming.TokenGenerateUseCase.TokenIssueCommand
import people.songpagu.infrastructure.jwt.config.properties.GoodGameJwtProperties
import people.songpagu.infrastructure.jwt.incoming.jjwt.JJwtTokenGenerateUseCase
import java.time.LocalDateTime

internal class JJwtTokenGeneratorTest {
    private val key = "11111222223333344444555556666677"

    @Test
    fun test1() {
        //given
        val generator = JJwtTokenGenerateUseCase(GoodGameJwtProperties(key).jjwtKey)

        //when
        println(
            generator.issue(
                TokenIssueCommand(
                    payload = "memberNumber",
                    expireDateTime = LocalDateTime.now().plusHours(1),
                    issueDateTime = LocalDateTime.now(),
                )
            )
        )

        //then
        assertThat(true).isEqualTo(true)

    }
}
