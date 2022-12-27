package com.example.common.base.service

import com.example.common.base.controller.dto.ValidDtoList

interface IService<E, CD, UD, RD> {
    fun entityClass(): Class<*>
    fun entityName(): String
    fun findById(id: Long): RD
    fun findAll(): List<RD>
    fun create(createDto: CD): RD
    fun createAll(createListDto: ValidDtoList<CD>)
    fun update(id: Long, updateDto: UD)
    fun updateAll(updateListDto: ValidDtoList<UD>)
    fun deleteById(id: Long)
}
