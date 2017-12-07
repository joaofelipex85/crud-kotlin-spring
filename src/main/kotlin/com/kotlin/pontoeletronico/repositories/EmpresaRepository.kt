package com.kotlin.pontoeletronico.repositories

import com.kotlin.pontoeletronico.documents.Empresa
import org.springframework.data.mongodb.repository.MongoRepository

interface EmpresaRepository : MongoRepository<Empresa, String> {

    fun findByCnpj(cnpj: String): Empresa

}