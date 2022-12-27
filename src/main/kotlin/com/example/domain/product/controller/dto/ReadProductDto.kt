package com.example.domain.product.controller.dto

import com.example.common.base.controller.dto.IReadDto
import com.example.domain.brand.controller.dto.ReadBrandDto
import com.example.domain.product.entity.constant.ProductStatus
import java.time.LocalDateTime

data class ReadProductDto(
    val id: Long?,
    val name: String,
    val price: Int,
    val brand: ReadBrandDto,
    val createdAt: LocalDateTime?,
    val modifiedAt: LocalDateTime?,
    val status: ProductStatus
) : IReadDto
