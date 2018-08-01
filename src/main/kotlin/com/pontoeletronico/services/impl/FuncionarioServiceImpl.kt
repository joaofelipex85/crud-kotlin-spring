package com.pontoeletronico.services.impl

import com.pontoeletronico.components.CadastroPFComponent
import com.pontoeletronico.documents.Empresa
import com.pontoeletronico.documents.Funcionario
import com.pontoeletronico.dtos.CadastroPFDto
import com.pontoeletronico.repositories.FuncionarioRepository
import com.pontoeletronico.response.Response
import com.pontoeletronico.services.EmpresaService
import com.pontoeletronico.services.FuncionarioService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.validation.BindingResult
import org.springframework.validation.ObjectError

@Service
class FuncionarioServiceImpl(val funcionarioRepository: FuncionarioRepository,
                             val empresaService: EmpresaService,
                             val cadastroPFComponent: CadastroPFComponent) : FuncionarioService {

    override fun persistir(funcionario: Funcionario): Funcionario = funcionarioRepository.save(funcionario)

    override fun buscarPorCpf(cpf: String): Funcionario? = funcionarioRepository.findByCpf(cpf)

    override fun buscarPorEmail(email: String): Funcionario? = funcionarioRepository.findByEmail(email)

    override fun buscarPorId(id: String): Funcionario? = funcionarioRepository.findOne(id)

    override fun cadastra(cadastroPFDto: CadastroPFDto, result: BindingResult): ResponseEntity<Response<CadastroPFDto>> {
        val response: Response<CadastroPFDto> = Response<CadastroPFDto>()

        val empresa: Empresa? = empresaService.buscarPorCnpj(cadastroPFDto.cnpj)
        validarDadosExistentes(cadastroPFDto, empresa, result)

        if (result.hasErrors()) {
            for (erro in result.allErrors) response.erros.add(erro.defaultMessage)
            return ResponseEntity.badRequest().body(response)
        }

        val funcionario: Funcionario = cadastroPFComponent.dtoToFuncionario(cadastroPFDto, empresa!!)

        persistir(funcionario)
        response.data = cadastroPFComponent.funcionarioToCadastroPFDto(funcionario, empresa!!)

        return ResponseEntity.ok(response)
    }

    private fun validarDadosExistentes(cadastroPFDto: CadastroPFDto, empresa: Empresa?,
                                       result: BindingResult) {
        if (empresa == null) {
            result.addError(ObjectError("empresa", "Empresa não cadastrada."))
        }

        val funcionarioCpf: Funcionario? = buscarPorCpf(cadastroPFDto.cpf)
        if (funcionarioCpf != null) {
            result.addError(ObjectError("funcionario", "CPF já existente."))
        }

        val funcionarioEmail: Funcionario? = buscarPorEmail(cadastroPFDto.email)
        if (funcionarioEmail != null) {
            result.addError(ObjectError("funcionario", "Email já existente."))
        }
    }

}
