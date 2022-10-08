package people.songpagu.goodgame.domain.exception

enum class GoodGameCode(val value: String, val exposureMessage: String) {
    OK("0000", "성공"),

    INTERNAL_SERVER_ERROR("E000", "서버에러"),

    LOGIN_FAIL("F000", "로그인 실패"),

    METHOD_ARGUMENT_NOT_VALID("M000",""),

    ACCESS_TOKEN_FAIL("T000", ""), //토큰 발급 실패
    ;
}
