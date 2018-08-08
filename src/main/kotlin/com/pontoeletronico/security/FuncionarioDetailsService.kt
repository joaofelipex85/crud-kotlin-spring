package com.pontoeletronico.security

import com.pontoeletronico.documents.Funcionario
import com.pontoeletronico.services.FuncionarioService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class FuncionarioDetailsService(val funcionarioService: FuncionarioService) : UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {

        username?.let { user ->

            val funcionario: Funcionario? = funcionarioService.buscarPorEmail(username)

            funcionario?.let {
                return FuncionarioPrincipal(funcionario)
            }

        }

        throw UsernameNotFoundException(username)

    }

}
