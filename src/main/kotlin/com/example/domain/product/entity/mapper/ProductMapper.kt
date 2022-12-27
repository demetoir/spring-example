package com.example.domain.product.entity.mapper

import com.example.common.base.entity.mapper.IMapper
import com.example.domain.brand.entity.Brand
import com.example.domain.brand.entity.mapper.BrandMapper
import com.example.domain.product.controller.dto.CreateProductDto
import com.example.domain.product.controller.dto.ReadProductDto
import com.example.domain.product.controller.dto.UpdateProductDto
import com.example.domain.product.entity.Product
import org.springframework.stereotype.Component
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Component
class ProductMapper(
    private val brandMapper: BrandMapper
) : IMapper<Product, CreateProductDto, UpdateProductDto, ReadProductDto> {

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    override fun toDto(entity: Product): ReadProductDto {
        return ReadProductDto(
            id = entity.id,
            name = entity.name,
            price = entity.price,
            brand = brandMapper.toDto(entity.brand),
            createdAt = entity.audit.createdAt,
            modifiedAt = entity.audit.modifiedAt,
            status = entity.status

        )
    }

    override fun toEntity(createDto: CreateProductDto): Product {
        return Product(
            name = createDto.name!!,
            price = createDto.price!!,
            brand = findBrand(createDto.brandId!!),
            status = createDto.status!!,
        )
    }

    override fun updateFromDto(updateDto: UpdateProductDto, entity: Product): Product {
        return entity.copy(
            name = updateDto.name ?: entity.name,
            price = updateDto.price ?: entity.price,
            brand = updateDto.brandId?.let { findBrand(it) } ?: entity.brand,
            status = updateDto.status ?: entity.status,
        )
    }

    fun findBrand(id: Long): Brand = entityManager.find(Brand::class.java, id)
}
