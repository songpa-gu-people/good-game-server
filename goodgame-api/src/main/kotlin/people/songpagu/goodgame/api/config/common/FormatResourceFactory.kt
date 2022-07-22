package people.songpagu.goodgame.api.config.common


import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer
import org.springframework.core.convert.converter.Converter
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object FormatResourceFactory {
    private val DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    private val DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    val parameterBinders: List<Converter<*, *>> = listOf(
        LocalDateParamBinder(DATE_FORMAT),
        LocalDateTimeParamBinder(DATE_TIME_FORMAT),
    )

    val jsonCustomizer: Jackson2ObjectMapperBuilderCustomizer =
        Jackson2ObjectMapperBuilderCustomizer { builder: Jackson2ObjectMapperBuilder ->
            builder.serializers(LocalDateTimeSerializer(DATE_TIME_FORMAT))
            builder.deserializers(LocalDateTimeDeserializer(DATE_TIME_FORMAT))
            builder.serializers(LocalDateSerializer(DATE_FORMAT))
            builder.deserializers(LocalDateDeserializer(DATE_FORMAT))
        }

}

class LocalDateParamBinder(
    private val format: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"),
) : Converter<String?, LocalDate> {

    override fun convert(source: String): LocalDate? {
        return takeUnless { source.trim().isBlank() }
            ?.let { LocalDate.parse(source, format) }
    }
}

class LocalDateTimeParamBinder(
    private val format: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"),
) : Converter<String?, LocalDateTime> {

    override fun convert(source: String): LocalDateTime? {
        return takeUnless { source.trim().isBlank() }
            ?.let { LocalDateTime.parse(source, format) }
    }
}
