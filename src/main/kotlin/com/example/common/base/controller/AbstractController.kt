package com.example.common.base.controller

import com.example.common.base.controller.dto.IReadDto
import com.example.common.base.controller.dto.IUpdateDto
import com.example.common.base.controller.dto.ValidDtoList
import com.example.common.base.response.ResponseDto
import com.example.common.base.service.AbstractService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import javax.validation.Valid

abstract class AbstractController<E, CD, UD, RD>(
    private val service: AbstractService<E, CD, UD, RD>
) : IController<CD, UD> where E : Any, UD : IUpdateDto, RD : IReadDto {
    @GetMapping("/{id}")
    override fun findById(@PathVariable id: Long): ResponseDto<Any> {
        return ResponseDto(HttpStatus.OK, service.findById(id))
    }

    @GetMapping()
    override fun findAll(): ResponseDto<Any> {
        return ResponseDto(HttpStatus.OK, service.findAll())
    }

    @PostMapping()
    override fun create(@RequestBody @Valid createDto: CD): ResponseDto<Any> {
        return ResponseDto(HttpStatus.CREATED, service.create(createDto))
    }

    @PostMapping("/bulk")
    override fun createAll(@RequestBody @Valid createDtoList: ValidDtoList<CD>): ResponseDto<Any> {
        service.createAll(createDtoList)
        return ResponseDto(HttpStatus.CREATED)
    }

    @PatchMapping("/{id}")
    override fun update(@PathVariable id: Long, @RequestBody @Valid updateDto: UD): ResponseDto<Any> {
        service.update(id, updateDto)
        return ResponseDto(HttpStatus.NO_CONTENT)
    }

    @PatchMapping("/bulk")
    override fun updateAll(@RequestBody @Valid updateDtoList: ValidDtoList<UD>): ResponseDto<Any> {
        service.updateAll(updateDtoList)
        return ResponseDto(HttpStatus.NO_CONTENT)
    }

    @DeleteMapping("/{id}")
    override fun delete(@PathVariable id: Long): ResponseDto<Any> {
        service.deleteById(id)
        return ResponseDto(HttpStatus.ACCEPTED)
    }
}
