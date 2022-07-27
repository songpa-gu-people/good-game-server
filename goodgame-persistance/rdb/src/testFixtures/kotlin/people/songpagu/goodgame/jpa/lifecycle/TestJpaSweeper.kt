package people.songpagu.goodgame.jpa.lifecycle

import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import people.songpagu.goodgame.jpa.domain.member.repository.LoginTokenJpaRepository
import people.songpagu.goodgame.jpa.domain.member.repository.MemberJpaRepository
import people.songpagu.goodgame.jpa.domain.member.repository.MemberLoginHistoryJpaRepository
import people.songpagu.goodgame.jpa.domain.member.repository.MemberPrivacyJpaRepository
import people.songpagu.infrastructure.log.Slf4j
import people.songpagu.infrastructure.log.Slf4j.Companion.log

@Slf4j
@Repository
class TestJpaSweeper(
    private val loginTokenJpaRepository: LoginTokenJpaRepository,

    private val memberLoginHistoryJpaRepository: MemberLoginHistoryJpaRepository,
    private val memberPrivacyJpaRepository: MemberPrivacyJpaRepository,
    private val memberJpaRepository: MemberJpaRepository,
) {

    @Transactional
    fun sweep() {
        log.info("jpa repository sweep start.")

        loginTokenJpaRepository.deleteAll()

        memberLoginHistoryJpaRepository.deleteAll()
        memberPrivacyJpaRepository.deleteAll()
        memberJpaRepository.deleteAll()

        log.info("jpa repository sweep end.")
    }
}
