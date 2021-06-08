package autor

import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post

@Controller("/autores")
class CadasraAutorController {

    @Post
    fun cadastra (@Body request: NovoAutorRequest){
        println(request)
    }

}