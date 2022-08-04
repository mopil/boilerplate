package example.boilerplate.utils

import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


/**
 * Logging 인터셉터 : 모든 요청에 대한 정보를 기록한다.
 */
class LoggingInterceptor : HandlerInterceptor {
    val log = logger()

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        log.info(
            "-------------- User Request {} -------------- {}: {}",
            request.remoteAddr,
            request.method,
            request.requestURL
        )
        return super.preHandle(request, response, handler)
    }

    override fun postHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        modelAndView: ModelAndView?
    ) {
        log.info("------------ Server Response ------------- result : {}", response.status)
        super.postHandle(request, response, handler, modelAndView)
    }
}