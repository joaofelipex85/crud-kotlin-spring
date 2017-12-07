package com.kotlin.pontoeletronico.services.impl

import com.kotlin.pontoeletronico.documents.Empresa
import com.kotlin.pontoeletronico.repositories.EmpresaRepository
import com.kotlin.pontoeletronico.services.EmpresaService
import org.springframework.stereotype.Service

@Service
class EmpresaServiceImpl(val empresaRepository: EmpresaRepository) : EmpresaService {

    override fun buscarPorCnpj(cnpj: String): Empresa? = empresaRepository.findByCnpj(cnpj)

    override fun persistir(empresa: Empresa): Empresa = empresaRepository.save(empresa)


}