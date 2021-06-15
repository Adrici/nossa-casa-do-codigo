package autor

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Put
import javax.transaction.Transactional

@Controller("/autores/{id}")
class AtualizaAutorController(val autorRepository: AutorRepository) {

    @Put
    @Transactional
    fun atualiza(@PathVariable id: Long, descricao:String) : HttpResponse<Any>{
        //buscar o objeto no banco
        val possivelAutor = autorRepository.findById(id)

        if (possivelAutor.isEmpty) {
            return HttpResponse.notFound()
        }

        val autor = possivelAutor.get()
        autor.descricao = descricao

        return HttpResponse.ok(DetalhesDoAutorResponse(autor))


    }

}
/*
 @Transactional => quando a transação é aberta,
todos os dados alterados serão salvos, sendo assim o autorRepository.update(autor.get())
 não é necessário*/