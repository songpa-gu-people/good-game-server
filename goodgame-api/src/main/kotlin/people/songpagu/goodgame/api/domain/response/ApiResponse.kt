package people.songpagu.goodgame.api.domain.response

sealed class ApiResponse<T>(
    val code: GoodGameResponseCode,
    val data: T? = null,
    val message: String,
) {
    class Ok<T>(data: T? = null, message: String) : ApiResponse<T>(code = GoodGameResponseCode.OK, data = data, message = message) {
        constructor(data: T? = null) : this(
            data = data,
            message = GoodGameResponseCode.OK.defaultMessage
        )

        constructor() : this(message = GoodGameResponseCode.OK.defaultMessage)
    }

    class Fail<T>(code: GoodGameResponseCode, data: T?, message: String) : ApiResponse<T>(code = code, data = data, message = message) {
        constructor() : this(
            code = GoodGameResponseCode.INTERNAL_SERVER_ERROR,
            data = null,
            message = GoodGameResponseCode.INTERNAL_SERVER_ERROR.defaultMessage
        )

        constructor(code: GoodGameResponseCode) : this(
            code = code,
            data = null,
            message = code.defaultMessage
        )

        constructor(code: GoodGameResponseCode, message: String) : this(
            code = code,
            data = null,
            message = message
        )
    }
}
