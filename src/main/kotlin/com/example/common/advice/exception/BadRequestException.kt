package com.example.common.advice.exception

open class BadRequestException(message: String?) : RuntimeException(message ?: "잘못된 요청입니다.")
