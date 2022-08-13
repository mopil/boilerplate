package example.boilerplate

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.mock.web.MockMultipartFile
import org.springframework.test.web.servlet.*

@SpringBootTest
@AutoConfigureMockMvc // MockMvc 를 사용하려면 필요하다
class SampleControllerTest {
    /**
     *  @WebMvcTest 와 @SpringBootTest 는 같이 쓸 수 없다.
     *  같이 쓰면 @Bootstrap 오류가 발생한다.
     */

    /**
     * 인텔리제이에서 의존성 주입을 할 수 없다고 빨간 밑줄이 그어지지만,
     * 상관없이 컴파일해서 테스트를 진행할 수 있음 (인텔리제이 문제인듯)
     */
    @Autowired lateinit var mvc: MockMvc
    @Autowired lateinit var mapper: ObjectMapper // JSON 변환 (body 변환 용)

    @Test
    fun `GET 요청`() {
        val result = mvc.get("/") {}
            .andExpect { status { isOk() } }
            .andExpect { jsonPath("id") { value("1") } } // JSON 바디 부분 체크
            .andDo { print() }
            .andReturn()

        jsonPrint(result)
    }

    @Test
    fun `POST 요청`() {
        val result = mvc.post("/") {
            contentType = MediaType.APPLICATION_JSON
            content = mapper.writeValueAsString("object")
        }
            .andExpect { status { isOk() } }
            .andDo{ print() }
            .andReturn()

        jsonPrint(result)
    }

    @Test
    fun `PUT 요청`() {
        val result = mvc.put("/") {
            contentType = MediaType.APPLICATION_JSON
            content = mapper.writeValueAsString("object")
            session = setMockSession() // MockSession 사용 예시
        }
            .andExpect { status { isOk() } }
            .andDo{ print() }
            .andReturn()

        jsonPrint(result)
    }

    @Test
    fun `DELETE 요청`() {
        mvc.delete("/") {}
            .andExpect { status { isOk() } }
            .andDo{ print() }
            .andReturn()
    }

    /**
     * 멀티파트 요청은 따로 보내야하고,
     * 무조건 POST 요청으로 들어간다 (그렇게 하드코딩 되어있다..)
     */
    @Test
    fun `멀티파트 요청`() {
        val img = MockMultipartFile("file", "test.jpg", "image/jpeg", "test".byteInputStream())
        mvc.multipart("/") {
            file(img)
        }
            .andDo { print() }
            .andExpect { status { isOk() } }
    }


}