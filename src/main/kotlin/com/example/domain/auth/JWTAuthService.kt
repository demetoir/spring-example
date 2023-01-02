package com.example.domain.auth

import com.example.config.logger
import com.example.domain.auth.dto.LoginByBasicAuthReq
import io.jsonwebtoken.Jwts
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Service
import javax.servlet.http.HttpServletRequest

@Service
class JWTAuthService(
    @Autowired
    private var jwtUtil: JWTUtil,

    @Autowired
    private var jwtConfig: JWTConfig
) {
    private val logger = logger()

    fun loginByBasicAuth(dto: LoginByBasicAuthReq): RawJWTString {
        // todo need better
        if (dto.password != "password") {
            throw RuntimeException("password is not correct")
        }

        if (dto.username != "username") {
            throw RuntimeException("username is not correct")
        }

        val claims = Jwts.claims().setSubject(dto.username)

        return jwtUtil.create(claims, jwtConfig.secretKey, jwtConfig.validTime)
    }

    fun loadAuthentication(token: RawJWTString): Authentication {
        val claims = jwtUtil.parseClaims(token = token, secretKey = jwtConfig.secretKey)

        val username = claims.body.subject
        val password = "password"
        val authorities = listOf(
            SimpleGrantedAuthority(UserRole.ROLE_USER.name)
        )

        return UsernamePasswordAuthenticationToken(
            username,
            password,
            // ! wtf 이거 안넣으면 Authenticated.false 로 처리되서 403 뜬다
            authorities
        )
    }

    fun isValidToken(token: RawJWTString): Boolean {
        return jwtUtil.isValid(token, jwtConfig.secretKey)
    }

    fun resolveToken(request: HttpServletRequest): RawJWTString? {
        return jwtUtil.resolveToken(request)
    }
}

enum class UserRole {
    ROLE_USER,
    ROLE_ADMIN
}
