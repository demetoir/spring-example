package com.example.domain.product.entity.repository

import com.example.common.base.entity.repository.IRepository
import com.example.domain.product.entity.Product
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : IRepository<Product>
