package people.songpagu.goodgame.domain.exception

enum class GoodGameCode(val value: String, val exposureMessage: String) {
    OK("0000", "성공"),

    LOGIN_FAIL("F000", "로그인 실패"),

    INTERNAL_SERVER_ERROR("E000", "서버에러"),
    ;

}
