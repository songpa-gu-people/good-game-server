package people.songpagu.goodgame.common

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GoodGameUuidGeneratorTest {
    @Test
    internal fun uuid_생성_테스트() {
        assertThat(GoodGameUuidGenerator.generate().length).isEqualTo(32)
    }
}