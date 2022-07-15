package people.songpagu.goodgame.security.domain.member.service

interface Oauth2MemberExtractor {
    fun enableExtract(registrationId: String): Boolean
    fun extract(attributes: Map<String, Any>): Oauth2ValidatedMember

    data class Oauth2ValidatedMember(val email: String)
}
