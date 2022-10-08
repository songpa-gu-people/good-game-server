package people.songpagu.goodgame.common

import java.util.*

class GoodGameUuidGenerator {
    companion object {
        fun generate(): String {
            return UUID.randomUUID().toString().replace("-", "")
        }
    }
}