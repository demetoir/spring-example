package com.example.config

import com.example.domain.auth.JWTAuthService
import com.example.domain.auth.JWTAuthenticationFilter
import com.example.domain.auth.UserRole
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@EnableWebSecurity
class WebSecurityConfig : WebMvcConfigurer {
    @Autowired
    private lateinit var jwtAuthService: JWTAuthService

    @Bean
    fun passwordEncoder(): PasswordEncoder? {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .formLogin().disable()
            .logout().disable()
            .headers().disable()
            // rest api 만을 고려하여 기본 설정은 해제.
            .httpBasic().disable()
            // csrf 보안 토큰 해제.
            .csrf().disable()
            // 토큰 기반 인증이므로 세션 역시 사용하지 않습니다.
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()

        // JwtAuthenticationFilter 를 UsernamePasswordAuthenticationFilter 전에 넣는다
        http.addFilterBefore(
            // ??? 이 필터가 적용되어도 401 케이스도 아닌 403 으로 나오는것 고치기
            JWTAuthenticationFilter(jwtAuthService),
            UsernamePasswordAuthenticationFilter::class.java
        )

        http //
            .authorizeHttpRequests()
            .antMatchers("/api/v1/auth/whoami").authenticated()
            .antMatchers("/api/v1/auth/secret").authenticated()
            .antMatchers("/api/v1/auth/public").permitAll()
            .antMatchers("/api/v1/auth/user").hasAuthority(UserRole.ROLE_USER.name)
            .antMatchers("/api/v1/auth/admin").hasAuthority(UserRole.ROLE_ADMIN.name)
            .antMatchers("/api/v1/brand/**").hasAuthority(UserRole.ROLE_USER.name)

            .antMatchers("/**").permitAll()

        return http.build()
    }
}
