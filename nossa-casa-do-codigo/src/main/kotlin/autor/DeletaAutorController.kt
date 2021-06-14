package autor

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.PathVariable
import javax.transaction.Transactional

//passa o id {} para indicar que é uma variável que está vindo pelo path da URI
@Controller("/autores/{id}")
class DeletaAutorController(val autorRepository: AutorRepository) {

    @Delete
    @Transactional
    fun deleta(@PathVariable id: Long) : HttpResponse<Any>  {
        val possivelAutor = autorRepository.findById(id)
        if (possivelAutor.isEmpty){
            return HttpResponse.notFound()
        }

        val autor = possivelAutor.get()
        autorRepository.deleteById(id)
        return HttpResponse.ok()
    }
}