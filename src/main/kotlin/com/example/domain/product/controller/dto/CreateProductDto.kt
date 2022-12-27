package com.example.domain.product.controller.dto

import com.example.domain.product.entity.constant.ProductStatus
import javax.validation.constraints.NotNull

data class CreateProductDto(
    @field:NotNull
    val name: String?,

    @field:NotNull
    val price: Int?,

    @field:NotNull
    val brandId: Long?,

    @field:NotNull
    val status: ProductStatus?,
)
