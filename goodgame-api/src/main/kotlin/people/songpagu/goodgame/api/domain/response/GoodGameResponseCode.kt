package people.songpagu.goodgame.api.domain.response

enum class GoodGameResponseCode(val value: String, val defaultMessage: String) {
    OK("0000", "성공"),
    INTERNAL_SERVER_ERROR("E000", "서버에러"),
    ;
}
