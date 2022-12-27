package com.example.common.advice.exception

open class NotFoundException(message: String?) : RuntimeException(message ?: "존재하지 않는 리소스입니다")
