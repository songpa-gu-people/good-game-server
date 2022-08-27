package people.songpagu.goodgame.domain.matching.option

import people.songpagu.goodgame.domain.config.CommonType

enum class District(
    private val _code: String,
    private val _desc: String
) : CommonType {
    JUNG_GU("JUNG_GU", "중구"),
    JONGRO_GU("JONGRO_GU", "종로구"),
    YONGSAN_GU("YONGSAN_GU", "용산구"),
    SEONGDONG_GU("SEONGDONG_GU", "성동구"),
    GWANGJIN_GU("GWANGJIN_GU", "광진구"),
    DONGDAEMUN_GU("DONGDAEMUN_GU", "동대문구"),
    JUNGNANG_GU("JUNGNANG_GU", "중랑구"),
    SEONGBUK_GU("SEONGBUK_GU", "성북구"),
    GANGBUK_GU("GANGBUK_GU", "강북구"),
    DOBONG_GU("DOBONG_GU", "도봉구"),
    NOWON_GU("NOWON_GU", "노원구"),
    EUNPYEONG_GU("EUNPYEONG_GU", "은평구"),
    SEODAEMUN_GU("SEODAEMUN_GU", "서대문구"),
    MAPO_GU("MAPO_GU", "마포구"),
    SEOCHO_GU("SEOCHO_GU", "서초구"),
    GANGNAM_GU("GANGNAM_GU", "강남구"),
    SONGPA_GU("SONGPA_GU", "송파구"),
    GANGDONG_GU("GANGDONG_GU", "강동구"),
    GANGSEO_GU("GANGSEO_GU", "강서구"),
    YANGCHEON_GU("YANGCHEON_GU", "양천구"),
    GURO_GU("GURO_GU", "구로구"),
    GEUMCHEON_GU("GEUMCHEON_GU", "금천구"),
    YEONGDEUNGPO_GU("YEONGDEUNGPO_GU", "영등포구"),
    DONGJAK_GU("DONGJAK_GU", "동작구"),
    GWANAK_GU("GWANAK_GU", "관악구"), ;

    companion object {
        fun ofCode(code: String): District {
            return values().first { it.code == code }
        }
    }

    override val desc: String
        get() = this._desc

    override val code: String
        get() = this._code

}
