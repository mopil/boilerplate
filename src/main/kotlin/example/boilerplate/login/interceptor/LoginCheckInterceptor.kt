
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class LoginCheckInterceptor : HandlerInterceptor {
    /**
     * 관리자 페이지 접근 시 로그인 체크하는 인터셉터
     */
    @Throws(Exception::class)
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val session = request.session
        if (session?.getAttribute("admin") == null) {
            throw Exception("인증 거부 : 허가되지 않은 사용자")
        }
        return true
    }
}
