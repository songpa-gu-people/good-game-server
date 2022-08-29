package people.songpagu.goodgame.jpa.domain.matching.converter

import people.songpagu.goodgame.jpa.config.mapper.JpaEntityMapper.jsonEntityMapper
import people.songpagu.goodgame.jpa.domain.matching.collection.DistrictCollection
import javax.persistence.AttributeConverter

class DistrictConverter : AttributeConverter<DistrictCollection, String> {
    override fun convertToDatabaseColumn(attribute: DistrictCollection): String {
        return jsonEntityMapper.writeValueAsString(attribute)
    }

    override fun convertToEntityAttribute(dbData: String?): DistrictCollection? {
        return dbData?.let { jsonEntityMapper.readValue(dbData, DistrictCollection::class.java) }
    }
}
