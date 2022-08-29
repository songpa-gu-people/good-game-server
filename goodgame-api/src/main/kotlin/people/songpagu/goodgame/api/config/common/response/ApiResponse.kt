package people.songpagu.goodgame.api.config.common.response

import people.songpagu.goodgame.domain.exception.GoodGameCode

sealed class ApiResponse<T>(
    val code: GoodGameCode,
    val data: T? = null,
    val message: String,
) {
    class Ok<T>(data: T? = null, message: String = GoodGameCode.OK.exposureMessage) :
        ApiResponse<T>(code = GoodGameCode.OK, data = data, message = message) {
        constructor(data: T? = null) : this(
            data = data,
            message = GoodGameCode.OK.exposureMessage,
        )
    }

    class Fail<T>(code: GoodGameCode, data: T?, message: String = code.exposureMessage) :
        ApiResponse<T>(code = code, data = data, message = message) {
        constructor() : this(
            code = GoodGameCode.INTERNAL_SERVER_ERROR,
            data = null,
            message = GoodGameCode.INTERNAL_SERVER_ERROR.exposureMessage,
        )

        constructor(code: GoodGameCode, message: String = code.exposureMessage) : this(
            code = code,
            data = null,
            message = message,
        )
    }
}
