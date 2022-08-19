package example.boilerplate.utils

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * 코틀린에서는 롬복을 사용할 수 없어서, @Slf4j를 사용할 수 없다.
 * 따라서 커스텀 로거를 만들어서 사용해야 한다.
 */
inline fun <reified T> T.logger(): Logger {
    return LoggerFactory.getLogger(T::class.java)
}

// request 헤더 정보들을 모아서 문자열로 만들어주는 함수
fun getHeadersAsString(request: HttpServletRequest): String {
    var headers = "headers = \n"
    request.headerNames.asIterator().forEach {
        headers += "$it : ${request.getHeader(it)}\n"
    }
    return headers
}

fun getHeadersAsString(response: HttpServletResponse): String {
    var headers = "headers = \n"
    response.headerNames.forEach {
        headers += "$it : ${response.getHeader(it)}\n"
    }
    return headers
}