package com.example.domain.auth.dto

import javax.validation.constraints.NotNull

data class LoginByBasicAuthRes(
    @field:NotNull
    val token: String
)
