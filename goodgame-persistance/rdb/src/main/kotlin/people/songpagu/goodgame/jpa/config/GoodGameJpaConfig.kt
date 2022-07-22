package people.songpagu.goodgame.jpa.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@EntityScan(
    value = [
        "people.songpagu.goodgame.jpa.domain",
    ]
)
@EnableJpaRepositories(
    value = [
        "people.songpagu.goodgame.jpa.domain",
    ]
)
@ComponentScan(
    value = [
        "people.songpagu.goodgame.jpa.domain",
    ]
)
@EnableJpaAuditing
@Import(QuerydslConfig::class)
@Configuration
class GoodGameJpaConfig

