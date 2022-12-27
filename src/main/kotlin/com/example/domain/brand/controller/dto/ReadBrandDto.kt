package com.example.domain.brand.controller.dto

import com.example.common.base.controller.dto.IReadDto
import java.time.LocalDateTime

data class ReadBrandDto(
    val id: Long?,
    val name: String,
    val createdAt: LocalDateTime?,
    val modifiedAt: LocalDateTime?,
) : IReadDto
