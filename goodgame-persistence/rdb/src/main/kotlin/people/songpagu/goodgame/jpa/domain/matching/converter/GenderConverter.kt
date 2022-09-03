package people.songpagu.goodgame.jpa.domain.matching.converter

import people.songpagu.goodgame.jpa.config.mapper.JpaEntityMapper.jsonEntityMapper
import people.songpagu.goodgame.jpa.domain.matching.collection.GenderCollection
import javax.persistence.AttributeConverter


class GenderConverter : AttributeConverter<GenderCollection, String> {
    override fun convertToDatabaseColumn(attribute: GenderCollection): String {
        return jsonEntityMapper.writeValueAsString(attribute)
    }

    override fun convertToEntityAttribute(dbData: String?): GenderCollection? {
        return dbData?.let { jsonEntityMapper.readValue(dbData, GenderCollection::class.java) }
    }
}
