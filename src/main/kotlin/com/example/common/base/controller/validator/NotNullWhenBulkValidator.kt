package com.example.common.base.controller.validator

import javax.servlet.http.HttpServletRequest
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class NotNullWhenBulkValidator(
    private val request: HttpServletRequest,
) : ConstraintValidator<NotNullWhenBulk, Long> {
    override fun isValid(value: Long?, context: ConstraintValidatorContext?): Boolean {
        if (request.method.equals("PATCH") && request.requestURI.endsWith("/bulk")) {
            return value != null
        }
        return true
    }
}
