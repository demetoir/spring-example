package com.example.domain.product.controller

import com.example.common.base.controller.AbstractController
import com.example.domain.product.controller.dto.CreateProductDto
import com.example.domain.product.controller.dto.ReadProductDto
import com.example.domain.product.controller.dto.UpdateProductDto
import com.example.domain.product.entity.Product
import com.example.domain.product.service.ProductService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/product")
class ProductController(
    private val productService: ProductService
) : AbstractController<Product, CreateProductDto, UpdateProductDto, ReadProductDto>(
    productService,
)
