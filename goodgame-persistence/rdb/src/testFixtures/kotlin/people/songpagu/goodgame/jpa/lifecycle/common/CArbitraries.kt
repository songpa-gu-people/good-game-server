package people.songpagu.goodgame.jpa.lifecycle.common

import net.bytebuddy.utility.RandomString
import java.util.*

/**
 * 랜덤 객체 발생기
 */
class CArbitraries {

    companion object {
        private val random = Random()

        fun strings(maxLength: Int = 32): String {
            val minLength: Int = 2
            val length = random.nextInt(maxLength - minLength + 1) + minLength
            return RandomString.make(length)
        }

        fun booleans(): Boolean {
            val booleans = mutableListOf(true, false)
            booleans.shuffle()
            return booleans[0]
        }

        fun specialChar(): String {
            val specialChars = mutableListOf("!", "@", "#", "$", "%", "&", "^", "*")
            specialChars.shuffle()
            return specialChars[0]
        }

        fun <T> randomFrom(collections: MutableList<T?>): T? {
            collections.shuffle()
            return collections[0]
        }
    }

}