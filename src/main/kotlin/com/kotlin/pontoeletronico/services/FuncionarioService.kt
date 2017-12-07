package com.kotlin.pontoeletronico.services

import com.kotlin.pontoeletronico.documents.Funcionario
import com.kotlin.pontoeletronico.dtos.CadastroPFDto
import com.kotlin.pontoeletronico.response.Response
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult

interface FuncionarioService {

    fun persistir(funcionario: Funcionario): Funcionario

    fun buscarPorCpf(cpf: String): Funcionario?

    fun buscarPorEmail(email: String): Funcionario?

    fun buscarPorId(id: String): Funcionario?

    fun cadastra(cadastroPFDto: CadastroPFDto, result: BindingResult): ResponseEntity<Response<CadastroPFDto>>

}