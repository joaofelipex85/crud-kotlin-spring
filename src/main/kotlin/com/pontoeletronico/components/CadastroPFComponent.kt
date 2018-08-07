package com.pontoeletronico.components

import com.pontoeletronico.documents.Empresa
import com.pontoeletronico.documents.Funcionario
import com.pontoeletronico.dtos.CadastroPFDto
import com.pontoeletronico.enums.PerfilEnum
import com.pontoeletronico.utils.SenhaUtils
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
