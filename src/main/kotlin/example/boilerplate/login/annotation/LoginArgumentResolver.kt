package example.boilerplate.login.annotation

import org.aspectj.weaver.Member
import org.springframework.core.MethodParameter
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import javax.security.auth.login.LoginException
import javax.servlet.http.HttpServletRequest

class LoginArgumentResolver : HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: MethodParameter): Boolean {
        // 로그인 어노테이션이 붙어 있는가를 검사
        val hasLoginAnnotation = parameter.hasParameterAnnotation(Login::class.java)

        // Member 타입의 객체가 로그인 어노테이션 뒤에 붙어 있는지 검사
        val hasMemberType: Boolean = Member::class.java.isAssignableFrom(parameter.parameterType)
        return hasLoginAnnotation && hasMemberType
    }

    // supportsParameter 가 true 면 이 메서드가 실행 됨
    @Throws(Exception::class)
    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any? {
        // 리퀘스트 뽑아 오기
        val request = webRequest.nativeRequest as HttpServletRequest

        // 리퀘스트에 있는 헤더값을 전체 출력 (테스트용)
        val session = request.getSession(false)
            ?: // 세션이 없으면 그냥 Member 에 null 이 들어감
            throw LoginException("로그인 되어 있지 않음.")

        // 세션 정보가 있으면 로그인된 회원을 날린다
        return session.getAttribute("SessionConst.LOGIN_MEMBER")
    }
}