package example.boilerplate

import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.mock.web.MockHttpSession
import org.springframework.test.web.servlet.MvcResult

/**
 * MockMvc Response Body JSON 결과를 예쁘게 출력하는 함수
 * MvcResult 는 MockMvc andReturn() 호출시 반환되는 값이다.
 * GSON 라이브러리로 파싱하기 때문에, GSON 의존성 추가가 필요하다.
 */
fun jsonPrint(result: MvcResult) {
    // 한글 깨짐 처리
    val body = String(result.response.contentAsString
        .toByteArray(Charsets.ISO_8859_1), Charsets.UTF_8)

    // GSON 을 이용한 JSON Pretty Print
    val gson = GsonBuilder().setPrettyPrinting().create()
    println(gson.toJson(JsonParser().parse(body)))
}


/**
 * 컨트롤러 테스트를 할 때, 세션값이 필요하면 MockSession 을 세팅하여 사용할 수 있다.
 * content 를 적는 바디부분에 session = setMockSession() 을 넣어주면 된다.
 * 인증 관련 테스트를 할 때 활용할 수 있다.
 */
fun setMockSession(): MockHttpSession {
    val session = MockHttpSession()
    session.setAttribute("키 값 아무거나", "객체 아무거나")
    return session
}
