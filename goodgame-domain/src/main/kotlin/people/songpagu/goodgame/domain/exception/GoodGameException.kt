package people.songpagu.goodgame.domain.exception

open class GoodGameException(
    val code: GoodGameCode = GoodGameCode.INTERNAL_SERVER_ERROR,
    val internalMessage: String? = null, // 내부에서 로그로 남길수 있는 메세지
    val exposureMessage: String? = code.exposureMessage, // 외부로 노출이 가능한 메세지
) : RuntimeException(exposureMessage)
