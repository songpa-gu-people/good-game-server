package people.songpagu.infrastructure.jwt.config.properties

import io.jsonwebtoken.security.Keys
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import java.nio.charset.StandardCharsets
import javax.crypto.SecretKey

@ConfigurationProperties(value = "goodgame.jwt")
@ConstructorBinding
data class GoodGameJwtProperties(
    val key: String,
){
    val jjwtKey:SecretKey = Keys.hmacShaKeyFor(key.toByteArray(StandardCharsets.UTF_8))
}
