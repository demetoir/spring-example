package com.example.common.base.response

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.util.Date

class ResponseDto<T> : ResponseEntity<ResponseDto.Response<T>> {
    constructor(httpStatus: HttpStatus) : super(
        Response(httpStatus),
        httpStatus
    )

    constructor(httpStatus: HttpStatus, data: T) : super(
        Response(httpStatus, data),
        httpStatus
    )

    constructor(httpStatus: HttpStatus, message: String?) : super(
        if (message == null) Response(httpStatus) else Response(httpStatus, message),
        httpStatus
    )

    constructor(httpStatus: HttpStatus, message: String?, data: T) : super(
        if (message == null) Response(httpStatus, data) else Response(httpStatus, message, data),
        httpStatus
    )

    data class Response<T>(val timestamp: Date, val status: Int, val message: String, val data: T?) {
        constructor(httpStatus: HttpStatus) : this(
            Date(),
            httpStatus.value(),
            httpStatus.reasonPhrase,
            null
        )

        constructor(httpStatus: HttpStatus, message: String) : this(
            Date(),
            httpStatus.value(),
            message,
            null
        )

        constructor(httpStatus: HttpStatus, data: T) : this(
            Date(),
            httpStatus.value(),
            httpStatus.reasonPhrase,
            data
        )

        constructor(httpStatus: HttpStatus, message: String, data: T) : this(
            Date(),
            httpStatus.value(),
            message,
            data
        )
    }
}
