package people.songpagu.goodgame.api.test

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import io.restassured.RestAssured
import io.restassured.specification.RequestSpecification
import org.junit.jupiter.api.BeforeEach
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.MediaType
import people.songpagu.goodgame.api.domain.response.ApiResponse


@GoodGameApiTestContext
abstract class GoodGameApiTest {
    private val objectMapper = ObjectMapper()

    @LocalServerPort
    var port = 0

    @BeforeEach
    fun setUp() {
        RestAssured.port = port
    }

    fun <T> getApi(
        path: String,
        token: String? = "",
        parameter: Map<String, String>? = mapOf(),
        responseType: TypeReference<ApiResponse.Ok<T>>
    ): ApiResponse.Ok<T> {
        val res: String = baseConfig(token, parameter)
            .get(path)
            .asString()
        return toCustomResponseFromMvcResult(res, responseType)
    }

    private fun baseConfig(token: String?, params: Map<String, String>?): RequestSpecification {
        return RestAssured.given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .params(params)
            .header("Authorization", token)
            .`when`()
    }

    private fun <T> toCustomResponseFromMvcResult(
        response: String?,
        typeReference: TypeReference<ApiResponse.Ok<T>>
    ): ApiResponse.Ok<T> {
        return try {
            objectMapper.readValue(response, typeReference)
        } catch (e: Exception) {
            throw IllegalArgumentException(e.message)
        }
    }
}