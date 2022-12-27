package com.example.common.base.entity.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
interface IRepository<E> : JpaRepository<E, Long>
