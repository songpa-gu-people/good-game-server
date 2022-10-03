package people.songpagu.goodgame.api.config.advice

import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import people.songpagu.goodgame.api.config.common.response.ApiResponse
import people.songpagu.goodgame.domain.exception.GoodGameCode
import people.songpagu.goodgame.domain.exception.GoodGameException
import people.songpagu.infrastructure.log.Slf4j
import people.songpagu.infrastructure.log.Slf4j.Companion.log

@Slf4j
@RestControllerAdvice
class ApiExceptionAdvice {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ApiResponse<Nothing> {
        log.error("API Unknown Error: {}", e.message, e)
        return ApiResponse.Fail()
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(GoodGameException::class)
    fun handleGoodGameException(e: GoodGameException): ApiResponse<Nothing> {
        log.error("API GoodGameException Error: {}", e.internalMessage ?: e.message, e)
        return ApiResponse.Fail(e.code)
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun methodArgumentNotValidException(e: MethodArgumentNotValidException): ApiResponse<Nothing> {
        val message: String = e.bindingResult.fieldError!!.defaultMessage!!
        return ApiResponse.Fail(
            code = GoodGameCode.METHOD_ARGUMENT_NOT_VALID,
            message = message
        )
    }
}
