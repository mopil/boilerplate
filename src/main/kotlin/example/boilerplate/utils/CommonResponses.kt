package example.boilerplate.utils

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError

/**
 * JSON 으로 bool 값을 리턴하고 싶을 때 사용한다.
 */
data class BoolResponse(val result: Boolean = true)

/**
 * 400 bad request 를 날리고 싶을 때 사용한다.
 * cause 는 예외가 발생한 도메인이고, message 는 상세 내용이다.
 */
data class ErrorResponse(val cause: String = "", val message: String = "")

/**
 * BindingResult 의 FieldError 를 JSON 형태의 ErrorResponse 로 변환한다.
 */
fun convertJson(error: FieldError) = ErrorResponse(VALIDATION_ERROR, error.field + " / " + error.defaultMessage)

fun convertJson(errors: List<FieldError>): ErrorListResponse {
    val result = mutableListOf<ErrorResponse>()
    errors.forEach { result.add(convertJson(it)) }
    return ErrorListResponse(result)
}

/**
 * ErrorResponse 를 여러개 내리고 싶을 때 사용한다.
 * List 를 그냥 내리면, JSON 으로 감싸진 형태가 아닌 List 형태로 내려가는데,
 * 그게 싫다면 이렇게 새로운 객체로 한번 감싸주면 된다.
 */
data class ErrorListResponse(val errors: List<ErrorResponse>)

/**
 * 400 bad request 를 내리고 싶을때 사용한다.
 * 예외 자체를 담는 것과, BindingResult 를 담는 두 가지를 구현했다.
 */
fun badRequest(ex: Exception) =
    ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(ErrorResponse(ex.javaClass.simpleName, ex.message!!))

fun badRequest(bindingResult: BindingResult) =
    ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(convertJson(bindingResult.fieldErrors))
