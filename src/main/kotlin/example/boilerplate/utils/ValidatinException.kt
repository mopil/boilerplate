package example.boilerplate.utils

import org.springframework.validation.BindingResult

const val VALIDATION_ERROR = "validation_error"

class ValidationException(var bindingResult: BindingResult): RuntimeException("validation error")