package people.songpagu.goodgame.jpa.domain.guild.entity

import people.songpagu.goodgame.common.GoodGameUuidGenerator
import people.songpagu.goodgame.jpa.domain.base.BaseEntity
import javax.persistence.*

const val GUILD_NAME_MAX_LENGTH: Int = 20

@Table(
    name = "guild",
    uniqueConstraints = [
        UniqueConstraint(name = "uk_guild_1", columnNames = ["guild_number"]),
    ],
    indexes = [
        Index(name = "idx_guild_1", columnList = "guild_name")
    ]
)
@Entity
class GuildEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(
        name = "guild_number",
        nullable = false,
        columnDefinition = "VARCHAR(32) COMMENT '길드번호'"
    )
    val guildNumber: String,

    @Column(
        name = "guild_name",
        nullable = false,
        columnDefinition = "VARCHAR(${GUILD_NAME_MAX_LENGTH}) COMMENT '길드이름'"
    )
    val guildName: String,

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY, mappedBy = "guildEntity", orphanRemoval = true)
    val guildMembers: MutableList<GuildMemberEntity>,
) : BaseEntity() {
    companion object {
        fun create(
            createMemberNumber: String,
            guildName: String
        ): GuildEntity {
            val guildEntity = GuildEntity(
                guildNumber = GoodGameUuidGenerator.generate(),
                guildName = guildName,
                guildMembers = mutableListOf(),
            )
            val masterGuildMember: GuildMemberEntity = GuildMemberEntity.ofMaster(
                memberNumber = createMemberNumber,
                guildEntity = guildEntity,
            )
            guildEntity.guildMembers.add(masterGuildMember)
            return guildEntity
        }
    }
}
