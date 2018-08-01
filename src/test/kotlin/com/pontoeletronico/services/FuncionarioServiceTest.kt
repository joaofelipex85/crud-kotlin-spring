package com.pontoeletronico.services

import com.pontoeletronico.documents.Funcionario
import com.pontoeletronico.enums.PerfilEnum
import com.pontoeletronico.repositories.FuncionarioRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class FuncionarioServiceTest {

    @Autowired
    val funcionarioService: FuncionarioService? = null

    @MockBean
    private val funcionarioRepository: FuncionarioRepository? = null

    private val EMAIL: String = "teste@teste.com"
    private val CPF: String = "cpf"
    private val ID: String = "1"

    @Before
    fun setUp() {
        BDDMockito.given(funcionarioRepository?.findByCpf(CPF)).willReturn(funcionario())
        BDDMockito.given(funcionarioRepository?.findByEmail(EMAIL)).willReturn(funcionario())
        BDDMockito.given(funcionarioRepository?.findOne(ID)).willReturn(funcionario())
        BDDMockito.given(funcionarioRepository?.save(funcionario())).willReturn(funcionario())
    }

    @Test
    fun testPersistirFuncionario() {
        val funcionario: Funcionario? = funcionarioService?.persistir(funcionario())
        Assert.assertNotNull(funcionario)
    }

    @Test
    fun testBuscarFuncionarioPorCpf() {
        val funcionario: Funcionario? = funcionarioService?.buscarPorCpf(CPF)
        Assert.assertNotNull(funcionario)
    }

    @Test
    fun testBuscarFuncionarioPorEmail() {
        val funcionario: Funcionario? = funcionarioService?.buscarPorEmail(EMAIL)
        Assert.assertNotNull(funcionario)
    }

    @Test
    fun testBuscarFuncionarioPorId() {
        val funcionario: Funcionario? = funcionarioService?.buscarPorId(ID)
        Assert.assertNotNull(funcionario)
    }

    private fun funcionario(): Funcionario = Funcionario("nome", "email", "senha", "cpf",
            PerfilEnum.ROLE_USUARIO, "1", 0.0, 0.0f, 0.0f, "1")

}