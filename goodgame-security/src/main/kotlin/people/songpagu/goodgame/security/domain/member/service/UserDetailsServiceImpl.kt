package people.songpagu.goodgame.security.domain.member.service

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component
import people.songpagu.goodgame.application.member.auth.join.outgoing.MemberFindPort
import people.songpagu.goodgame.security.domain.member.UserDetailsImpl

@Component
class UserDetailsServiceImpl(private val memberFindPort: MemberFindPort) : UserDetailsService {
    override fun loadUserByUsername(memberNumber: String): UserDetails {
        val findByMemberNum =
            memberFindPort.findByMemberNum(memberNumber) ?: throw UsernameNotFoundException("존재하지 않는 유저 입니다.")
        return UserDetailsImpl(findByMemberNum.memberNumber)
    }

}