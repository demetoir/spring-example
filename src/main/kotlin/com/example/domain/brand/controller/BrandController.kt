package com.example.domain.brand.controller

import com.example.common.base.controller.AbstractController
import com.example.domain.brand.controller.dto.CreateBrandDto
import com.example.domain.brand.controller.dto.ReadBrandDto
import com.example.domain.brand.controller.dto.UpdateBrandDto
import com.example.domain.brand.entity.Brand
import com.example.domain.brand.service.BrandService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/brand")
class BrandController(
    private val brandService: BrandService
) : AbstractController<Brand, CreateBrandDto, UpdateBrandDto, ReadBrandDto>(
    brandService
)
