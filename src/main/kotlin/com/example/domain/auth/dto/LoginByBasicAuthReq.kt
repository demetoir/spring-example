package com.example.domain.auth.dto

import javax.validation.constraints.NotNull

data class LoginByBasicAuthReq(
    @field:NotNull
    val username: String,

    @field:NotNull
    val password: String
)
