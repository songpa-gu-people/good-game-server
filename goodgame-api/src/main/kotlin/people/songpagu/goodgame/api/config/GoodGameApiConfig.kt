package people.songpagu.goodgame.api.config

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import people.songpagu.goodgame.jpa.config.GoodGameJpaConfig

@ComponentScan(
    basePackages = [
        "people.songpagu.goodgame.api.domain"
    ]
)
@Import(
    value = [
        GoodGameJpaConfig::class,
    ]
)
@Configuration
class GoodGameApiConfig
