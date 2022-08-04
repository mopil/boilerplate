package example.boilerplate.utils

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class AppConfig : WebMvcConfigurer {
    /**
     * Logging 인터셉터를 등록한다.
     */
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(LoggingInterceptor())
            .order(1)
            .addPathPatterns("/**")
    }

    /**
     * CORS 를 모든 트래픽으로 설정한다.
     * 리액트와 같은 SPA 프론트와 통신할 때 마주하는 CORS 를 손 쉽게 해결한다.
     * 실제 서비스 배포시 꼭 필요한 트래픽만 접근 가능하도록 제한할 필요가 있다.
     */
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins("*")
    }

    /**
     * 서버에서 지정된 경로의 정적 리소스 (ex.이미지 파일)를 사용자에게 내려주고 싶을때 사용한다.
     * /file/image/test.jpg 와 같이 GET 요청을 보내면 해당 정적 리소스가 사용자에게 내려간다.
     * 이미지는 자동으로 랜더링되서 보여진다.
     */
    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("/file/image/**")
            .addResourceLocations("file:" + "정적 파일이 저장된 경로")
    }
}