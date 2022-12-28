package com.example.domain.brand.service

import com.example.common.base.service.AbstractService
import com.example.domain.brand.controller.dto.CreateBrandDto
import com.example.domain.brand.controller.dto.ReadBrandDto
import com.example.domain.brand.controller.dto.UpdateBrandDto
import com.example.domain.brand.entity.Brand
import com.example.domain.brand.entity.mapper.BrandMapper
import com.example.domain.brand.entity.repository.BrandRepository
import org.springframework.stereotype.Service
import javax.validation.constraints.NotBlank

@Service
class BrandService(
    private val brandMapper: BrandMapper,
    private val brandRepository: BrandRepository
) : AbstractService<Brand, CreateBrandDto, UpdateBrandDto, ReadBrandDto>(
    brandMapper,
    brandRepository
)

class BrandCreateRequest {
    @field:NotBlank
    val name: String? = null

    val dto: BrandCreateDto
}

class BrandCreateDto {
    @field:NotBlank
    val name: String? = null
}
