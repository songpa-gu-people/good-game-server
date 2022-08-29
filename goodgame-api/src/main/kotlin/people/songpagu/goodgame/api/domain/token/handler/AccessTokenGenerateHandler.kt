package people.songpagu.goodgame.api.domain.token.handler

import people.songpagu.goodgame.api.config.layer.Handler
import people.songpagu.goodgame.api.domain.token.adapter.AccessTokenGenerateAdapter
import java.time.LocalDateTime

@Handler
class AccessTokenGenerateHandler(
    private val accessTokenGenerateAdapter: AccessTokenGenerateAdapter,
) {
    fun issue(refreshToken: String, issueDateTime: LocalDateTime = LocalDateTime.now()): String {
        return accessTokenGenerateAdapter.issue(refreshToken, issueDateTime)
    }
}
