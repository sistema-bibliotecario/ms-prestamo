package pe.cibertec.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Persona {

    private String dni;
    private String nombres;
    private String apellidos;
    private int edad;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaNac;
    private String direccion;
    private String email;
    private String telefono;
}
