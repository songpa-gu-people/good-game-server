package people.songpagu.goodgame.api.main

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import
import people.songpagu.goodgame.api.config.GoodGameApiConfig

@Import(GoodGameApiConfig::class)
@SpringBootApplication
class GoodGameApiApplication

fun main(vararg args: String) {
    runApplication<GoodGameApiApplication>(*args)
}
