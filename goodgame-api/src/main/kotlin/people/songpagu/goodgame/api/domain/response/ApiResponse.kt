package people.songpagu.goodgame.api.domain.response

import people.songpagu.goodgame.domain.exception.GoodGameCode

sealed class ApiResponse<T>(
    val code: GoodGameCode,
    val data: T? = null,
    val message: String,
) {
    class Ok<T>(data: T? = null, message: String) : ApiResponse<T>(code = GoodGameCode.OK, data = data, message = message) {
        constructor(data: T? = null) : this(
            data = data,
            message = GoodGameCode.OK.exposureMessage
        )

        constructor() : this(message = GoodGameCode.OK.exposureMessage)
    }

    class Fail<T>(code: GoodGameCode, data: T?, message: String) : ApiResponse<T>(code = code, data = data, message = message) {
        constructor() : this(
            code = GoodGameCode.INTERNAL_SERVER_ERROR,
            data = null,
            message = GoodGameCode.INTERNAL_SERVER_ERROR.exposureMessage
        )

        constructor(code: GoodGameCode) : this(
            code = code,
            data = null,
            message = code.exposureMessage
        )

        constructor(code: GoodGameCode, message: String) : this(
            code = code,
            data = null,
            message = message
        )
    }
}
