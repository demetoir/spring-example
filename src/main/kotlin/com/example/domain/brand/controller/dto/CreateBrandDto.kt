package com.example.domain.brand.controller.dto

import javax.validation.constraints.NotNull

data class CreateBrandDto(
    @field:NotNull
    val name: String?,
)
