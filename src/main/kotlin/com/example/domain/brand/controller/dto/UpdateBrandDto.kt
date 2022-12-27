package com.example.domain.brand.controller.dto

import com.example.common.base.controller.dto.IUpdateDto
import com.example.common.base.controller.validator.NotNullWhenBulk
import com.example.common.base.service.exception.RequiredArgumentException

data class UpdateBrandDto(
    @field:NotNullWhenBulk
    val id: Long? = null,
    val name: String?,
) : IUpdateDto {
    override fun getId(): Long {
        if (id == null) throw RequiredArgumentException("id")
        return id
    }
}
