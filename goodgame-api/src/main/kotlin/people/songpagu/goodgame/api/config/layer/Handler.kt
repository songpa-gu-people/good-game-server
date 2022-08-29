package people.songpagu.goodgame.api.config.layer

import org.springframework.stereotype.Component


/**
 * `Controller`에 의해 호출되며 `@Transactional`이 존재하지 않는다.
 *
 * `Adapter`와 `UseCase`를 관리한다.
 */
@Component
annotation class Handler
