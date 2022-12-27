package com.example.domain.product.service

import com.example.common.base.service.AbstractService
import com.example.domain.product.controller.dto.CreateProductDto
import com.example.domain.product.controller.dto.ReadProductDto
import com.example.domain.product.controller.dto.UpdateProductDto
import com.example.domain.product.entity.Product
import com.example.domain.product.entity.mapper.ProductMapper
import com.example.domain.product.entity.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productMapper: ProductMapper,
    private val productRepository: ProductRepository
) : AbstractService<Product, CreateProductDto, UpdateProductDto, ReadProductDto>(
    productMapper,
    productRepository
)
