package people.songpagu.goodgame.jpa.domain.matching.converter

import people.songpagu.goodgame.domain.matching.option.type.District
import javax.persistence.AttributeConverter
import kotlin.streams.toList

private const val DELIMITER = ","

class DistrictConverter : AttributeConverter<List<District>, String> {
    override fun convertToDatabaseColumn(attribute: List<District>?): String {
        if (attribute == null) {
            return ""
        }
        return attribute.stream()
            .map { it.code }
            .toList()
            .joinToString(DELIMITER)
    }

    override fun convertToEntityAttribute(dbData: String?): List<District> {
        if (dbData.isNullOrBlank()) {
            return mutableListOf()
        }
        return dbData.split(DELIMITER).stream()
            .map { District.ofCode(it) }
            .toList()
    }
}
