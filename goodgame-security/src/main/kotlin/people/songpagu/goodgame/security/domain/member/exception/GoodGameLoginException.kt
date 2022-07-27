package people.songpagu.goodgame.security.domain.member.exception

import people.songpagu.goodgame.domain.exception.GoodGameCode
import people.songpagu.goodgame.domain.exception.GoodGameException

class GoodGameLoginException(exposureMessage: String) :
    GoodGameException(code = GoodGameCode.LOGIN_FAIL, exposureMessage = exposureMessage)
