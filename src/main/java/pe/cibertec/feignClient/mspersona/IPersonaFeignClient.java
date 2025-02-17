package pe.cibertec.feignClient.mspersona;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pe.cibertec.entity.Persona;

@FeignClient(name = "ms-persona", url = "http://localhost:9001")
public interface IPersonaFeignClient {

    @GetMapping("/api/ms-persona/personaDni")
    Persona obtenerPorDni(@RequestParam String dni);
}
