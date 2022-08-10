package example.boilerplate.swagger

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SwaggerConfig {
    @Bean
    fun swagger(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .useDefaultResponseMessages(false)
//            .ignoredParameterTypes(Date::class.java, Member::class.java) // API 명세서에 넣지 않을 객체 명시
            .forCodeGeneration(true)
            .select()
            .apis(RequestHandlerSelectors.basePackage("hntech.hntechserver")) // 프로젝트 루트 디렉토리
            .paths(PathSelectors.any())
            .build()
            .apiInfo(apiInfo())
            .enable(true)
    }

    private fun apiInfo(): ApiInfo {
        return ApiInfoBuilder()
            .title("API 명세서")
            .description("API 명세서")
            .build()
    }
}