package com.kotlin.pontoeletronico.controllers

import com.kotlin.pontoeletronico.dtos.CadastroPFDto
import com.kotlin.pontoeletronico.response.Response
import com.kotlin.pontoeletronico.services.FuncionarioService
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/cadastrar-pf")
class CadastroPFController(
        val funcionarioService: FuncionarioService) {

    @PostMapping
    fun cadastrar(@Valid @RequestBody cadastroPFDto: CadastroPFDto,
                  result: BindingResult): ResponseEntity<Response<CadastroPFDto>> {

        return funcionarioService.cadastra(cadastroPFDto, result)
    }

}