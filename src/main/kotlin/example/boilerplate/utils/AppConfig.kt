package example.boilerplate.utils

import com.querydsl.jpa.impl.JPAQueryFactory
import example.boilerplate.logging.JsonLoggingInterceptor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Configuration
class AppConfig(private val loggingInterceptor: JsonLoggingInterceptor) : WebMvcConfigurer {
    /**
     * Logging 인터셉터를 등록한다.
     */
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(loggingInterceptor)
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
            .allowedOrigins("http://127.0.0.1:5173") // vite 쓰는 리액트에서 오리진 요청을 열어줘야함 (포트 주의)
            .allowedMethods("*") // http 모든 메소드 요청 허용
            .allowedHeaders("*") // 헤더 정보 모두 허용
            .allowCredentials(true) // 쿠키, 세션 정보도 허용
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

// JPAQueryFactory 빈 등록
@Configuration
class QuerydslConfig(@PersistenceContext private val em: EntityManager) {
    @Bean
    fun jpaQueryFactory(): JPAQueryFactory = JPAQueryFactory(this.em)
}