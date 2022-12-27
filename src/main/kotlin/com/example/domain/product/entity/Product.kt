package com.example.domain.product.entity

import com.example.common.base.entity.AuditingEntity
import com.example.domain.brand.entity.Brand
import com.example.domain.product.entity.constant.ProductStatus
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import javax.persistence.Column
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.EntityListeners
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity(name = "tb_product")
@EntityListeners(AuditingEntityListener::class)
data class Product(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    val price: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", nullable = false)
    val brand: Brand,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    val status: ProductStatus,

    @Embedded
    val audit: AuditingEntity = AuditingEntity(),
) {
    fun changeBrand(brand: Brand) = this.copy(brand = brand)
}
