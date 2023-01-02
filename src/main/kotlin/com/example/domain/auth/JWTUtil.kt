package com.example.domain.auth

import com.example.config.logger
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.util.Date
import javax.servlet.http.HttpServletRequest

// https://velog.io/@jkijki12/Spirng-Security-Jwt-%EB%A1%9C%EA%B7%B8%EC%9D%B8-%EC%A0%81%EC%9A%A9%ED%95%98%EA%B8%B0

// * spring security config
typealias RawJWTString = String

@Component
class JWTUtil() {

    private val logger = logger()

    fun create(claims: Claims, secretKey: String, tokenValidTime: Long): RawJWTString {
        val issueAt = Date()
        val expireAt = Date(issueAt.time + tokenValidTime)

        return Jwts.builder() //
            .setClaims(claims)
            .setIssuedAt(issueAt)
            .setExpiration(expireAt)
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact()
    }

    fun isValid(token: RawJWTString, secretKey: String): Boolean {
        lateinit var claims: Jws<Claims>
        try {
            claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
        } catch (e: Exception) {
            logger.info("token is invalid by ${e.message}")
            return false
        }

        val isExpired = claims.body.expiration.before(Date())

        return !isExpired
    }

    fun parseClaims(token: RawJWTString, secretKey: String): Jws<Claims> {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
    }

    fun resolveToken(request: HttpServletRequest): RawJWTString? {
        val value = request.getHeader("Authorization")
        if (value != null && value.startsWith("Bearer ")) {
            return value.substring(7)
        }

        return null
    }
}
