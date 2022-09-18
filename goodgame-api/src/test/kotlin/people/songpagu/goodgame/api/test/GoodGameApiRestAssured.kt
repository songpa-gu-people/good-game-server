package people.songpagu.goodgame.api.test

import com.fasterxml.jackson.core.type.TypeReference
import io.restassured.RestAssured
import io.restassured.specification.RequestSpecification
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.MediaType
import people.songpagu.goodgame.api.config.common.response.ApiResponse
import people.songpagu.goodgame.jpa.config.mapper.JpaEntityMapper.jsonEntityMapper
import people.songpagu.goodgame.jpa.lifecycle.TestJpaSweeper

@GoodGameApiTestContext
abstract class GoodGameApiRestAssured {
    @Suppress("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private lateinit var testJpaSweeper: TestJpaSweeper

    @LocalServerPort
    var port = 0

    @BeforeEach
    fun setUp() {
        RestAssured.port = port
        testJpaSweeper.sweep()
    }

    protected fun <T> getApi(
        path: String,
        token: String? = "",
        parameter: Map<String, String>? = mapOf(),
        responseType: TypeReference<ApiResponse.Ok<T>>
    ): ApiResponse.Ok<T> {
        val res: String = baseConfig(token, parameter)
            .get(path)
            .asString()
        return jsonEntityMapper.readValue(res, responseType)
    }

    protected fun <T> postApi(
        path: String,
        token: String? = "",
        body: Any?,
        responseType: TypeReference<ApiResponse.Ok<T>>
    ): ApiResponse.Ok<T> {
        val res: String = baseConfig(token, mapOf())
            .body(jsonEntityMapper.writeValueAsString(body))
            .post(path)
            .asString()
        return jsonEntityMapper.readValue(res, responseType)
    }

    private fun baseConfig(token: String?, params: Map<String, String>?): RequestSpecification {
        return RestAssured.given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .params(params)
            .header("Authorization", token)
            .`when`()
    }
}
