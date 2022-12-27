package com.example.domain.product.controller.dto

import com.example.common.base.controller.dto.IUpdateDto
import com.example.common.base.controller.validator.NotNullWhenBulk
import com.example.common.base.service.exception.RequiredArgumentException
import com.example.domain.product.entity.constant.ProductStatus

class UpdateProductDto(
    @field:NotNullWhenBulk
    val id: Long? = null,
    val name: String?,
    val price: Int?,
    val brandId: Long?,
    val status: ProductStatus
) : IUpdateDto {
    override fun getId(): Long {
        if (id == null) throw RequiredArgumentException("id")
        return id
    }
}
