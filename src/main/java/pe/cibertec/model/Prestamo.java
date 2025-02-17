package pe.cibertec.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_Prestamo")
public class Prestamo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPrestamo;
    private String isbn;
    private String titulo;
    private String dniPersona;
    private String NombreCompleto;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaInicio;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaTermino;
}


