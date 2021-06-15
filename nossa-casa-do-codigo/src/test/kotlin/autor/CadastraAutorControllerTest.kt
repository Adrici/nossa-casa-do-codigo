package autor

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.net.http.HttpClient
import javax.inject.Inject

@MicronautTest
internal class CadastraAutorControllerTest{
    @field:Inject
    lateinit var enderecoClient: EnderecoClient

    @field:Inject
    @field:Client("/")

    //Lateinit: "atrasa a inicialização"
    lateinit var client: HttpClient

    @Test
    fun `deve cadastrar um novo autor`() {
        // cenario
        val novoAutorRequest = NovoAutorRequest("Edu", "edu@gmail.com", "Education Programing",
            "04321-111", "11")
        val enderecoResponse = EnderecoResponse("Rua EduLog", "São Paulo", "SP")

        Mockito.`when`(enderecoClient.consulta(novoAutorRequest.cep))
            .thenReturn(HttpResponse.ok(enderecoResponse))

        val request = HttpRequest.POST("/autores", novoAutorRequest)

        // acao
        val response = client.toBlocking().exchange(request, Any::class.java)

        // corretude
        assertEquals(HttpStatus.CREATED, response.status)
        assertTrue(response.headers.contains("Location"))
        assertTrue(response.header("Location")!!.matches("/autores/\\d".toRegex()))
    }

    @MockBean(EnderecoClient::class)
    fun enderecoMock():EnderecoClient {
        return Mockito.mock(EnderecoClient::class.java)
    }


}

