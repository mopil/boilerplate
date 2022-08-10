package example.boilerplate.login.interceptor

import LoginCheckInterceptor
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

class LoginCheckAppConfig : WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(LoginCheckInterceptor())
            .order(2)
            .addPathPatterns("/admin/**")
            .excludePathPatterns("/admin/login", "/admin", "/admin/info")
    }
}