package com.example.common.advice

import com.example.common.advice.exception.BadRequestException
import com.example.common.advice.exception.NotFoundException
import com.example.common.advice.exception.UnsupportedOperationException
import com.example.common.base.response.ResponseDto
import com.example.config.logger
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    private val log = logger()

    @ExceptionHandler(BadRequestException::class)
    fun handleBadRequestException(e: BadRequestException): ResponseDto<Void> {
        log.info("bad request exception {}", e.message)
        return ResponseDto(HttpStatus.BAD_REQUEST, e.message)
    }

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(e: NotFoundException): ResponseDto<Void> {
        log.info("not found exception {}", e.message)
        return ResponseDto(HttpStatus.NOT_FOUND, e.message)
    }

    @ExceptionHandler(UnsupportedOperationException::class)
    fun handleUnsupportedOperationException(e: UnsupportedOperationException): ResponseDto<Void> {
        log.info("unsupported operation exception {}", e.message)
        return ResponseDto(HttpStatus.BAD_REQUEST, e.message)
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageNotReadableException(e: HttpMessageNotReadableException): ResponseDto<Void> {
        log.warn("http message not readable exception {}", e.message)
        return ResponseDto(HttpStatus.BAD_REQUEST, e.message)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException): ResponseDto<Void> {
        val firstFieldError = e.bindingResult.allErrors[0] as FieldError
        val message = "${firstFieldError.field}: ${firstFieldError.defaultMessage}"
        return ResponseDto(HttpStatus.BAD_REQUEST, message)
    }

    @ExceptionHandler(Exception::class)
    fun unHandleException(e: Exception): ResponseDto<Void> {
        log.error("unhandled exception {}", e.message)
        log.error(e.stackTraceToString())
        return ResponseDto(HttpStatus.INTERNAL_SERVER_ERROR, e.message)
    }
}
