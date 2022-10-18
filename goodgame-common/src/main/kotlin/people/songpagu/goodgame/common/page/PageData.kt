package people.songpagu.goodgame.common.page

class PageData<T>(
    val contents: List<T>,
    val totalPages: Int, // 전체 페이지 수
    val totalElements: Long, // 전체 컨텐츠 수
    val pageNumber: Int, // 현재 페이지 넘버
    val size: Int, // 요청한 컨텐츠 수
    val numberOfElements: Int, // 현재 페이지에 노출되는 컨텐츠 수
    val isFirst: Boolean, // 첫페이지인지 여부
    val isLast: Boolean, // 마지막페이지인지 여부
    val isEmpty: Boolean // contents 가 비어있는지 여부
)
