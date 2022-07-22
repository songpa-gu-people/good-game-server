package people.songpagu.goodgame.security.domain.oauth.handler

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class GoodGameQueryStringTest {

    @DisplayName("queryString parse test1")
    @Test
    fun test1() {
        //given
        val queryString = "redirect_uri=http"

        //when
        val sut = GoodGameQueryString(queryString)

        //then
        val expect = GoodGameQueryString(
            mapOf(
                "redirect_uri" to listOf("http")
            )
        )

        assertThat(sut).isEqualTo(expect)
    }

    @DisplayName("queryString parse test2")
    @Test
    fun test2() {
        //given
        val queryString = ""

        //when
        val sut = GoodGameQueryString(queryString)

        //then
        val expect = GoodGameQueryString(emptyMap())

        assertThat(sut).isEqualTo(expect)
    }

    @DisplayName("queryString parse test3")
    @Test
    fun test3() {
        //given
        val queryString = "redirect_uri=http&redirect_uri=https"

        //when
        val sut = GoodGameQueryString(queryString)

        //then
        val expect = GoodGameQueryString(
            mapOf(
                "redirect_uri" to listOf(
                    "http",
                    "https"
                )
            )
        )

        assertThat(sut).isEqualTo(expect)
    }

    @DisplayName("queryString parse test4")
    @Test
    fun test4() {
        //given
        val queryString = "redirect_uri=http&redirect_uri=https"

        //when
        val sut = GoodGameQueryString(queryString)
        val values = sut.get("redirect_uri")

        //then
        assertThat(values).isEqualTo(listOf("http", "https"))
    }

}
