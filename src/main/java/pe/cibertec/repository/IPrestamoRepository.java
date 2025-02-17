package pe.cibertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.cibertec.dto.response.PrestamoResponse;
import pe.cibertec.model.Prestamo;

import java.util.List;
import java.util.Optional;

public interface IPrestamoRepository extends JpaRepository<Prestamo, Integer> {
}
