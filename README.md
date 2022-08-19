# Spring Boot Kotlin Boilerplate
스프링 부트 + 코틀린 환경에서 자주 사용하는 boilerplate 기능 모음

- application.yml 
- 파일 업로드/다운로드 전체 기능 (Entity, Controller, Service, Repository)
- 전역 요청 로깅 인터셉터
- 생성일, 수정일 도입용 BaseTimeEntity
- REST API 관련 공통 Response DTO (Error, Bool, BadRequest)
- BindingResult 검증 처리용 커스텀 예외
- @Slf4j 대체용 커스텀 로거
- Swagger 설정
- 로그인 처리 (인터셉터, 어노테이션 + ArgumentResolver)
- Kotlin DSL을 사용한 MockMvc 사용 예제
- 테스트 코드 작성시 유용하게 사용될 수 있는 함수들 (TestUtils)
- Request body, Response body 로깅을 위한 Wrapping class
