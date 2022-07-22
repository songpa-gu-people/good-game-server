package people.songpagu.goodgame.jpa.domain.member.entity

import people.songpagu.goodgame.jpa.domain.base.BaseEntity
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Index
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(
    name = "member_login_history",
    indexes = [
        Index(name = "idx_member_login_history_1", columnList = "member_id")
    ],
)
class MemberLoginHistoryEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST])
    @JoinColumn(name = "member_id", columnDefinition = "BIGINT COMMENT '회원 아이디'")
    val memberEntity: MemberEntity,
) : BaseEntity()
