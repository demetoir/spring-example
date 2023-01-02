package com.example.domain.auth

import org.springframework.stereotype.Component

@Component
class JWTConfig {
    final var secretKey = "34q98nt29p3xn84t24398ycnp2340p89ynt2x3p098y4t23498ypt23498ypn2x3p9y84n2xpy983n4"
        private set

    // * 30분
    final var validTime = 30 * 60 * 1000L
        private set
}
