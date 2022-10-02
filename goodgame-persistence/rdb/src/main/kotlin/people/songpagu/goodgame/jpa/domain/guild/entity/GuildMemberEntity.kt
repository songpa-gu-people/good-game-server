package people.songpagu.goodgame.jpa.domain.guild.entity

import people.songpagu.goodgame.domain.guild.type.GuildMemberRank
import javax.persistence.*

@Table(
    name = "guild"
)
@Entity
class GuildMemberEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(
        name = "guild_number",
        nullable = false,
        columnDefinition = "VARCHAR(32) COMMENT '멤버번호'"
    )
    val memberNumber: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guild_num")
    val guildEntity: GuildEntity,

    _guildMemberRank: GuildMemberRank,
) {
    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "VARCHAR(128) COMMENT '길드 멤버 등급'")
    var guildMemberRank: GuildMemberRank = _guildMemberRank
        private set

    companion object {
        fun ofMaster(
            memberNumber: String,
            guildEntity: GuildEntity
        ): GuildMemberEntity {
            return GuildMemberEntity(
                memberNumber = memberNumber,
                guildEntity = guildEntity,
                _guildMemberRank = GuildMemberRank.MASTER,
            )
        }
    }
}