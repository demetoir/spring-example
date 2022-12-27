package com.example.domain.brand.entity

import com.example.common.base.entity.AuditingEntity
import com.example.domain.product.entity.Product
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.EntityListeners
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity(name = "tb_brand")
@EntityListeners(AuditingEntityListener::class)
data class Brand(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val name: String,

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL], mappedBy = "brand")
    val products: MutableList<Product> = mutableListOf(),

    @Embedded
    val audit: AuditingEntity = AuditingEntity(),
) {
    fun addProduct(product: Product) {
        this.products.add(product)
    }
}
