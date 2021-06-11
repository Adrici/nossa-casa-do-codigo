package autor

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.PathVariable

//passa o id {} para indicar que é uma variável que está vindo pelo path da URI
@Controller("/autores/{id}")
class DeletaAutorController(val autorRepository: AutorRepository) {

    @Delete
    fun deleta(@PathVariable id: Long) : HttpResponse<Any>  {
        val possivelAutor = autorRepository.findById(id)
        if (possivelAutor.isEmpty){
            return HttpResponse.notFound()
        }
        autorRepository.deleteById(id)
        return HttpResponse.ok()
    }
}