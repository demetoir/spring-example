package com.example.common.base.service

import com.example.common.base.controller.dto.IReadDto
import com.example.common.base.controller.dto.IUpdateDto
import com.example.common.base.controller.dto.ValidDtoList
import com.example.common.base.entity.mapper.IMapper
import com.example.common.base.entity.repository.IRepository
import com.example.common.base.service.exception.NotFoundEntityException
import java.lang.reflect.ParameterizedType

abstract class AbstractService<E, CD, UD, RD>(
    private val mapper: IMapper<E, CD, UD, RD>,
    private val repository: IRepository<E>
) : IService<E, CD, UD, RD> where E : Any, UD : IUpdateDto, RD : IReadDto {

    override fun entityClass(): Class<*> {
        val type = javaClass.genericSuperclass as ParameterizedType
        return type.actualTypeArguments[0] as Class<*>
    }

    override fun entityName(): String {
        return entityClass().simpleName
    }

    override fun findById(id: Long): RD {
        val entity = repository.findById(id).orElseThrow { NotFoundEntityException(entityName()) }
        return mapper.toDto(entity)
    }

    override fun findAll(): List<RD> {
        return repository.findAll().map { mapper.toDto(it) }
    }

    override fun create(createDto: CD): RD {
        return mapper.toDto(repository.save(mapper.toEntity(createDto)))
    }

    override fun createAll(createListDto: ValidDtoList<CD>) {
        createListDto.list.map { create(it) }
    }

    override fun update(id: Long, updateDto: UD) {
        val entity = repository.findById(id).orElseThrow { NotFoundEntityException(entityName()) }
        repository.save(mapper.updateFromDto(updateDto, entity))
    }

    override fun updateAll(updateListDto: ValidDtoList<UD>) {
        updateListDto.list.map { update(it.getId(), it) }
    }

    override fun deleteById(id: Long) {
        val entity = repository.findById(id).orElseThrow { NotFoundEntityException(entityName()) }
        repository.delete(entity)
    }
}
