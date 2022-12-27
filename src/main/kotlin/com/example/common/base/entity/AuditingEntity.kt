package com.example.common.base.entity

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class AuditingEntity(
    @CreatedDate
    @Column(nullable = false, updatable = false)
    var createdAt: LocalDateTime? = null,

    @LastModifiedDate
    @Column(nullable = false, updatable = true)
    var modifiedAt: LocalDateTime? = null,

    @Column(nullable = true, updatable = true)
    var deletedAt: LocalDateTime? = null,

    @CreatedBy
//    @Column(nullable = false, updatable = false)
    @Column(nullable = true, updatable = false)
    var createdId: Long? = null,

    @LastModifiedBy
//    @Column(nullable = false, updatable = true)
    @Column(nullable = true, updatable = true)
    var modifiedId: Long? = null,
)
