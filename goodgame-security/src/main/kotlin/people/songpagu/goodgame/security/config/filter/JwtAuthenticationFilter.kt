package people.songpagu.goodgame.security.config.filter

import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.filter.OncePerRequestFilter
import people.songpagu.goodgame.application.token.decode.incoming.TokenAuthenticateUseCase
import people.songpagu.goodgame.security.domain.member.service.UserDetailsServiceImpl
import java.time.LocalDateTime
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthenticationFilter(
    private val tokenAuthenticateUseCase: TokenAuthenticateUseCase,
    private val userDetailServiceImpl: UserDetailsServiceImpl
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authentication = getAuthentication(request)

        if (authentication != null) {
            val context = SecurityContextHolder.getContext()
            context.authentication = authentication
        }
        super.doFilter(request, response, filterChain)
    }

    private fun getAuthentication(request: HttpServletRequest): Authentication? {
        val accessToken = request.getHeader(HttpHeaders.AUTHORIZATION)
        if (accessToken.isNullOrBlank()) {
            return null
        }
        return try {
            val memberNumber = tokenAuthenticateUseCase.validate(accessToken, LocalDateTime.now())
            val userDetails: UserDetails = userDetailServiceImpl.loadUserByUsername(memberNumber)
            UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
        } catch (e: Exception) {
            null
        }
    }
}