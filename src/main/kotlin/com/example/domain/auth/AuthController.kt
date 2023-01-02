package com.example.domain.auth

import com.example.common.base.response.ResponseDto
import com.example.domain.auth.dto.LoginByBasicAuthReq
import com.example.domain.auth.dto.LoginByBasicAuthRes
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/auth")
class AuthController() {

    @Autowired
    private lateinit var jwtAuthService: JWTAuthService

    @PostMapping("/login/basic")
    fun loginByBasicAuth(
        @RequestBody @Valid
        dto: LoginByBasicAuthReq
    ): ResponseDto<LoginByBasicAuthRes> {
        val token = jwtAuthService.loginByBasicAuth(dto)

        return ResponseDto(HttpStatus.OK, LoginByBasicAuthRes(token))
    }

    @PostMapping("/logout")
    fun logout(): ResponseDto<Any> {
        return ResponseDto(HttpStatus.OK, {
        })
    }

    @GetMapping("/whoami")
    fun whoAmI(): ResponseDto<Any> {
        return ResponseDto(HttpStatus.OK, {
        })
    }

    @GetMapping("/secret")
    fun secret(): ResponseDto<Any> {
        return ResponseDto(HttpStatus.OK, {
        })
    }

    @GetMapping("/public")
    fun public(): ResponseDto<Any> {
        return ResponseDto(HttpStatus.OK, {
        })
    }

    @GetMapping("/admin")
    fun admin(): ResponseDto<Any> {
        return ResponseDto(HttpStatus.OK, {
        })
    }

    @GetMapping("/user")
    fun user(): ResponseDto<Any> {
        return ResponseDto(HttpStatus.OK, {
        })
    }
}
