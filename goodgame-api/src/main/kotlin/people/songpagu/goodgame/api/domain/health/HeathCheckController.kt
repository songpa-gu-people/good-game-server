package people.songpagu.goodgame.api.domain.health

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class HeathCheckController {
    @GetMapping("/health")
    fun healthCheck(): Map<String, String> {
        return mapOf("status" to "OK")
    }
}
