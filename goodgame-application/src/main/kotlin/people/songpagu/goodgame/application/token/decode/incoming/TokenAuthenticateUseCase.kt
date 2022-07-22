package people.songpagu.goodgame.application.token.decode.incoming

import java.time.LocalDateTime

interface TokenAuthenticateUseCase {
    fun validate(token: String, validateTime: LocalDateTime): String
}
