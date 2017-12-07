package com.kotlin.pontoeletronico.services

import com.kotlin.pontoeletronico.documents.Empresa

interface EmpresaService {

    fun buscarPorCnpj(cnpj: String): Empresa?

    fun persistir(empresa: Empresa): Empresa

}