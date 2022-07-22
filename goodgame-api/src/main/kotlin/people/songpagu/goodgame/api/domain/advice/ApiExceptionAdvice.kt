package people.songpagu.goodgame.api.domain.advice

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import people.songpagu.goodgame.api.domain.response.ApiResponse
import people.songpagu.infrastructure.log.Slf4j
import people.songpagu.infrastructure.log.Slf4j.Companion.log

@Slf4j
@RestControllerAdvice
class ApiExceptionAdvice {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ApiResponse<Nothing> {
        log.error("API Unknown Error: {}", e.toString(), e)
        return ApiResponse.Fail()
    }
}
