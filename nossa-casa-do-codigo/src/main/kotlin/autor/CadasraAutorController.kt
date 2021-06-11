package autor

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated
import javax.validation.Valid

@Validated
@Controller("/autores")
class CadasraAutorController (val autorRepository: AutorRepository) {

    @Post
    fun cadastra (@Body @Valid request: NovoAutorRequest) : HttpResponse<Any>{
        // request = objeto de domínio
        println("Requisicao => ${request}")

       val autor = request.paraAutor()

        println("Autor => ${autor.nome}")
        autorRepository.save(autor)

        val uri = UriBuilder.of("/autores/{id}")
            .expand(mutableMapOf(Pair("id", autor.id)))
        return HttpResponse.created(uri)
    }
}