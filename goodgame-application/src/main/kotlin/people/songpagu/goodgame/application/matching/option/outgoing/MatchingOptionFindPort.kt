package people.songpagu.goodgame.application.matching.option.outgoing

import people.songpagu.goodgame.domain.matching.option.model.MatchingOption

interface MatchingOptionFindPort {
    fun findByMemberNumber(memberNumber: String): MatchingOption?
}