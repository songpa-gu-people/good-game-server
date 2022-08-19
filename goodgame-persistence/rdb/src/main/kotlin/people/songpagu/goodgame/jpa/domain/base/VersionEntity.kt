package people.songpagu.goodgame.jpa.domain.base

import javax.persistence.Column
import javax.persistence.MappedSuperclass
import javax.persistence.Version

@MappedSuperclass
abstract class VersionEntity {
    @Version
    @Column(name = "version", columnDefinition = "bigint comment 'Data Version'")
    var version: Long = 0
        protected set
}
