package people.songpagu.goodgame.common

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.string.shouldHaveLength

class GoodGameUuidGeneratorTest : BehaviorSpec({
    given("goodgame 제공 UUID 생성기는") {
        val sut = GoodGameUuidGenerator

        `when`("UUID 생성시") {
            val uuid = sut.generate()

            then("32자리이다.") {
                uuid shouldHaveLength 32
            }
        }
    }
})
