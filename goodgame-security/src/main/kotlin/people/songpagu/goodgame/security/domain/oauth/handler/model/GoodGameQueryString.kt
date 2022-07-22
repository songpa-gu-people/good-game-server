package people.songpagu.goodgame.security.domain.oauth.handler.model

data class GoodGameQueryString(
    private val query: Map<String, List<String>>,
) {
    constructor(queryString: String?) : this(queryString?.parseQuery() ?: emptyMap())

    fun get(key: String): List<String> {
        return query[key] ?: emptyList()
    }

    data class KeyValue(
        val key: String,
        val value: String,
    ) {
        companion object {
            fun of(keyValue: String): KeyValue {
                val (key, value) = keyValue.split("=")
                return KeyValue(key = key, value = value)
            }
        }
    }

    companion object {
        const val GOOD_GAME_REDIRECT_URI = "redirect_uri"
    }
}

private fun String.parseQuery(): Map<String, List<String>> {
    if (this.isEmpty()) {
        return emptyMap()
    }

    val keyValueCollection: List<String> = this.split("&")

    return keyValueCollection.map { GoodGameQueryString.KeyValue.of(it) }
        .groupBy(keySelector = { it.key }, valueTransform = { it.value })
}
