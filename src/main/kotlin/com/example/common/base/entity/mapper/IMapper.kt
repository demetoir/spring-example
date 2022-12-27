package com.example.common.base.entity.mapper

interface IMapper<E, CD, UD, RD> {
    fun toDto(entity: E): RD
    fun toDto(entityList: Collection<E>): MutableList<RD> = entityList.map { toDto(it) }.toMutableList()
    fun toEntity(createDto: CD): E
    fun toEntity(createDtoList: Collection<CD>): MutableList<E> = createDtoList.map { toEntity(it) }.toMutableList()
    fun updateFromDto(updateDto: UD, entity: E): E
}
