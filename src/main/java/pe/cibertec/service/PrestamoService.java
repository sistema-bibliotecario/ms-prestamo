package pe.cibertec.service;

import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pe.cibertec.dto.request.PrestamoRequest;
import pe.cibertec.dto.response.PrestamoResponse;
import pe.cibertec.entity.Libro;
import pe.cibertec.entity.Persona;
import pe.cibertec.feignClient.mslibro.ILibroFeignClient;
import pe.cibertec.feignClient.mspersona.IPersonaFeignClient;
import pe.cibertec.model.Prestamo;
import pe.cibertec.repository.IPrestamoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PrestamoService {

    @Autowired
    IPrestamoRepository prestamoRepository;

    @Autowired
    IPersonaFeignClient personaFeignClient;

    @Autowired
    ILibroFeignClient libroFeignClient;

    public List<PrestamoResponse> listPrestamos() {
        return Optional.of(prestamoRepository.findAll())
                .filter(prestamos -> !prestamos.isEmpty())
                .map(prestamos -> prestamos.stream()
                        .map(p -> new PrestamoResponse(
                                p.getTitulo(),
                                p.getFechaInicio(),
                                p.getFechaTermino()
                        ))
                        .collect(Collectors.toList()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontraron préstamos"));
    }


    public Prestamo obtenerPrestamo(Integer id) {
        return prestamoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Prestamo no encontrado"));
    }

    public void agregarPrestamo(PrestamoRequest request) {
        try {
            Persona persona = obtenerPersonaPorDni(request.getDniPersona());

            Libro libro = obtenerLibroPorIsbn(request.getIsbn());

            Prestamo prestamo = new Prestamo();
            prestamo.setIsbn(request.getIsbn());
            prestamo.setTitulo(libro.getTitulo());
            prestamo.setDniPersona(request.getDniPersona());
            prestamo.setNombreCompleto(persona.getNombres() + " " + persona.getApellidos());
            prestamo.setFechaInicio(request.getFechaInicio());
            prestamo.setFechaTermino(request.getFechaTermino());
            prestamoRepository.save(prestamo);

        } catch (FeignException e) {
            throw new RuntimeException("Error al comunicarse con un servicio externo: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Ocurrió un error inesperado: " + e.getMessage());
        }
    }

    public void deletePrestamo(Integer prestamo) {
        prestamoRepository.deleteById(prestamo);
    }

    public String actualizarPrestamo(Integer id, PrestamoRequest request) {
        return prestamoRepository.findById(id)
                .map(existingPrestamo -> {
                    Persona persona = obtenerPersonaPorDni(request.getDniPersona());

                    Libro libro = obtenerLibroPorIsbn(request.getIsbn());

                    existingPrestamo.setIsbn(request.getIsbn());
                    existingPrestamo.setTitulo(libro.getTitulo());
                    existingPrestamo.setDniPersona(request.getDniPersona());
                    existingPrestamo.setNombreCompleto(persona.getNombres() + " " + persona.getApellidos());
                    existingPrestamo.setFechaInicio(request.getFechaInicio());
                    existingPrestamo.setFechaTermino(request.getFechaTermino());
                    prestamoRepository.save(existingPrestamo);
                    return "Prestamo actualizado correctamente";
                })
                .orElseThrow(() -> new RuntimeException("Prestamo no existe con ID: " + id));
    }

    private Persona obtenerPersonaPorDni(String dni) {
        try {
            return Optional.ofNullable(personaFeignClient.obtenerPorDni(dni))
                    .orElseThrow(() -> new RuntimeException("La persona con DNI " + dni + " no está registrada"));
        } catch (FeignException.NotFound e) {
            throw new RuntimeException("La persona con DNI " + dni + " no está registrada");
        }
    }

    private Libro obtenerLibroPorIsbn(String isbn) {
        try {
            return Optional.ofNullable(libroFeignClient.obtenerPorIsbn(isbn))
                    .filter(l -> l.getStock() > 0)
                    .orElseThrow(() -> new RuntimeException("El libro con ISBN " + isbn + " no existe o no tiene stock disponible"));
        } catch (FeignException.NotFound e) {
            throw new RuntimeException("El libro con ISBN " + isbn + " no existe o no tiene stock disponible");
        }
    }

}
