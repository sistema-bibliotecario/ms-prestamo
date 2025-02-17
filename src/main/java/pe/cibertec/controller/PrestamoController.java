package pe.cibertec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.cibertec.dto.request.PrestamoRequest;
import pe.cibertec.dto.response.PrestamoResponse;
import pe.cibertec.model.Prestamo;
import pe.cibertec.service.PrestamoService;

import java.util.List;

@RestController
@RequestMapping("/api/ms-prestamo")
public class PrestamoController {

    @Autowired
    PrestamoService prestamoService;

    @GetMapping("/listAllPrestamos")
    public List<PrestamoResponse> getAllPrestamos() {
        return prestamoService.listPrestamos();
    }

    @GetMapping("/obtenerPrestamo/{id}")
    public Prestamo obtenerPrestamo(@PathVariable Integer id) {
        return prestamoService.obtenerPrestamo(id);
    }

    @PostMapping("/addPrestamo")
    public void agregarPrestamo(@RequestBody PrestamoRequest request) {
        prestamoService.agregarPrestamo(request);
    }

    @DeleteMapping("/deletePrestamo/{code}")
    public void deletePrestamo(@PathVariable int code) {
        prestamoService.deletePrestamo(code);
    }

    @PutMapping("/updatePrestamo/{code}")
    public ResponseEntity<String> actualizarPrestamo(@PathVariable int code, @RequestBody PrestamoRequest request) {
        try {
            String result = prestamoService.actualizarPrestamo(code, request);
            return ResponseEntity.ok(result);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

}
