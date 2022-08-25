package people.songpagu.goodgame.jpa.domain.matching.converter

import people.songpagu.goodgame.domain.member.type.Gender
import javax.persistence.AttributeConverter
import kotlin.streams.toList

private const val DELIMITER = ","

class GenderConverter : AttributeConverter<List<Gender>, String> {
    override fun convertToDatabaseColumn(attribute: List<Gender>?): String {
        if (attribute == null) {
            return ""
        }
        return attribute.stream()
            .map { it.name }
            .toList()
            .joinToString(DELIMITER)
    }

    override fun convertToEntityAttribute(dbData: String?): List<Gender> {
        if (dbData.isNullOrBlank()) {
            return mutableListOf()
        }
        return dbData.split(DELIMITER).stream()
            .map { Gender.valueOf(it) }
            .toList()
    }
}