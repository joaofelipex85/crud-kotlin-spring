package com.kotlin.pontoeletronico.components

import com.kotlin.pontoeletronico.documents.Empresa
import com.kotlin.pontoeletronico.documents.Funcionario
import com.kotlin.pontoeletronico.dtos.CadastroPFDto
import com.kotlin.pontoeletronico.enums.PerfilEnum
import com.kotlin.pontoeletronico.utils.SenhaUtils
import org.springframework.stereotype.Component

@Component
class CadastroPFComponent {

    fun dtoToFuncionario(cadastroPFDto: CadastroPFDto, empresa: Empresa) =
            Funcionario(cadastroPFDto.nome, cadastroPFDto.email,
                    SenhaUtils().gerarBcrypt(cadastroPFDto.senha), cadastroPFDto.cpf,
                    PerfilEnum.ROLE_USUARIO, empresa.id.toString(),
                    cadastroPFDto.valorHora?.toDouble(), cadastroPFDto.qtdHorasTrabalhoDia?.toFloat(),
                    cadastroPFDto.qtdHorasAlmoco?.toFloat(), cadastroPFDto.id)


    fun funcionarioToCadastroPFDto(funcionario: Funcionario, empresa: Empresa): CadastroPFDto =
            CadastroPFDto(funcionario.nome, funcionario.email, "", funcionario.cpf,
                    empresa.cnpj, empresa.id.toString(), funcionario.valorHora.toString(),
                    funcionario.qtdHorasTrabalhoDia.toString(),
                    funcionario.qtdHorasTrabalhoDia.toString(),
                    funcionario.id)

}