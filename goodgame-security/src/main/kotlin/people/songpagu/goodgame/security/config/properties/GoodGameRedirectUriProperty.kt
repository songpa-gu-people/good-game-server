package people.songpagu.goodgame.security.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties(value = "goodgame.security.redirect-uri")
@ConstructorBinding
class GoodGameRedirectUriProperty(
    val loginFail: String
)