package people.songpagu.goodgame.jpa.domain.member.entity

import people.songpagu.goodgame.domain.member.type.LoginType
import people.songpagu.goodgame.domain.member.type.MemberStatus
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.UniqueConstraint


@Entity
@Table(
    name = "member",
    uniqueConstraints = [
        UniqueConstraint(name = "uk_member_1", columnNames = ["member_number"]),
        UniqueConstraint(name = "uk_member_2", columnNames = ["auth_id", "login_type"]),
    ],
)
class MemberEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long? = null,

    @Column(name = "member_number", nullable = false, columnDefinition = "VARCHAR(32) COMMENT '회원번호'")
    val memberNumber: String,

    @Column(name = "auth_id", nullable = false, columnDefinition = "VARCHAR(128) COMMENT '회원인증 아이디'")
    val authId: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "login_type", nullable = false, columnDefinition = "VARCHAR(20) COMMENT '로그인 타입'")
    val loginType: LoginType,

    _status: MemberStatus,
) {
    @Column(name = "status", columnDefinition = "VARCHAR(20) COMMENT '회원상태'")
    @Enumerated
    var status: MemberStatus = _status
        private set

    companion object {
        fun create(memberNumber: String, authId: String, loginType: LoginType): MemberEntity {
            return MemberEntity(
                memberNumber = memberNumber,
                authId = authId,
                loginType = loginType,
                _status = MemberStatus.ENABLE
            )
        }
    }
}
