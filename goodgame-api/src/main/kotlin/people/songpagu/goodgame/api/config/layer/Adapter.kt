package people.songpagu.goodgame.api.config.layer

import org.springframework.core.annotation.AliasFor
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * 다수의 `UseCase`를 하나의 `@Transaction`으로 관리한다.
 */
@Service
@Transactional
annotation class Adapter(
    @get:AliasFor(annotation = Transactional::class, attribute = "readOnly")
    val readOnly: Boolean = false,
)
