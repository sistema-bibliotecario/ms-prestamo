package pe.cibertec.dto.request;

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
public class PrestamoRequest {
    private String isbn;
    private String dniPersona;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaInicio;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaTermino;
}
