package people.songpagu.goodgame.jpa.domain.guild.entity

import people.songpagu.goodgame.domain.guild.type.GuildMemberRole
import javax.persistence.*

@IdClass(GuildMemberEntityId::class)
@Table(
    name = "guild_member"
)
@Entity
class GuildMemberEntity(
    @Id
    @Column(
        name = "member_number",
        nullable = false,
        columnDefinition = "VARCHAR(32) COMMENT '멤버번호'"
    )
    val memberNumber: String,

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guild_id")
    val guildEntity: GuildEntity,

    _guildMemberRole: GuildMemberRole,
) {
    @Enumerated(EnumType.STRING)
    @Column(name = "guild_member_role", columnDefinition = "VARCHAR(128) COMMENT '길드 멤버 등급'")
    var guildMemberRole: GuildMemberRole = _guildMemberRole
        private set

    companion object {
        fun ofMaster(
            memberNumber: String,
            guildEntity: GuildEntity
        ): GuildMemberEntity {
            return GuildMemberEntity(
                memberNumber = memberNumber,
                guildEntity = guildEntity,
                _guildMemberRole = GuildMemberRole.MASTER,
            )
        }
    }
}