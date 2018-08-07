package com.pontoeletronico.services

import com.pontoeletronico.documents.Empresa

interface EmpresaService {

    fun buscarPorCnpj(cnpj: String): Empresa?

    fun save(empresa: Empresa): Empresa

}
