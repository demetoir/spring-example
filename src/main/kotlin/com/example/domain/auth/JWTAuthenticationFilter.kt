package com.example.domain.auth

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

class JWTAuthenticationFilter(
    private var service: JWTAuthService

) : GenericFilterBean() {

    @Throws(IOException::class, ServletException::class)
    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val token = service.resolveToken(request as HttpServletRequest)

        if (token == null) {
            logger.debug("token is null")
            return chain.doFilter(request, response)
        }

        if (!service.isValidToken(token)) {
            logger.debug("token is not valid")
            return chain.doFilter(request, response)
        }

        val authentication = service.loadAuthentication(token)
        SecurityContextHolder.getContext().authentication = authentication
        logger.debug("authentication: $authentication")

        return chain.doFilter(request, response)
    }
}
