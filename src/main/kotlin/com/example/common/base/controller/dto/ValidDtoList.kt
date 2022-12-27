package com.example.common.base.controller.dto

import javax.validation.Valid
import javax.validation.constraints.NotNull

class ValidDtoList<T>(
    @field:Valid
    @field:NotNull
    val list: List<T>
)
