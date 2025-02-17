package pe.cibertec.feignClient.mslibro;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pe.cibertec.entity.Libro;

@FeignClient(name = "ms-libro", url = "http://localhost:9002")
public interface ILibroFeignClient {

    @GetMapping("/api/ms-libro/libroPorIsbn")
    Libro obtenerPorIsbn(@RequestParam String isbn);
}
