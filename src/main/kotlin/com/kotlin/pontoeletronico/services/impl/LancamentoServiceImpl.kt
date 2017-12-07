package com.kotlin.pontoeletronico.services.impl

import com.kotlin.pontoeletronico.documents.Lancamento
import com.kotlin.pontoeletronico.repositories.LancamentoRepository
import com.kotlin.pontoeletronico.services.LancamentoService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class LancamentoServiceImpl(val lancamentoRepository: LancamentoRepository) : LancamentoService {

    override fun buscarPorFuncionarioId(funcionarioId: String, pageRequest: PageRequest): Page<Lancamento> =
            lancamentoRepository.findByFuncionarioId(funcionarioId, pageRequest)

    override fun buscarPorId(id: String): Lancamento? = lancamentoRepository.findOne(id)

    override fun persistir(lancamento: Lancamento): Lancamento? = lancamentoRepository.save(lancamento)

    override fun remover(id: String) {
        lancamentoRepository.delete(id)
    }
}