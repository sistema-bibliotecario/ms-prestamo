package pe.cibertec.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrestamoResponse {
    private String titulo;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaInicio;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaTermino;
}
