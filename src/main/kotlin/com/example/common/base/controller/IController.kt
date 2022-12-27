package com.example.common.base.controller

import com.example.common.base.controller.dto.ValidDtoList
import com.example.common.base.response.ResponseDto
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody

interface IController<CD, UD> {
    fun findById(@PathVariable id: Long): ResponseDto<Any>
    fun findAll(): ResponseDto<Any>
    fun create(@RequestBody createDto: CD): ResponseDto<Any>
    fun createAll(@RequestBody createDtoList: ValidDtoList<CD>): ResponseDto<Any>
    fun update(@PathVariable id: Long, @RequestBody updateDto: UD): ResponseDto<Any>
    fun updateAll(@RequestBody updateDtoList: ValidDtoList<UD>): ResponseDto<Any>
    fun delete(@PathVariable id: Long): ResponseDto<Any>
}
