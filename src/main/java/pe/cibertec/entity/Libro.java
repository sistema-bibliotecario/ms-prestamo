package pe.cibertec.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Libro {

    private String isbn;
    private String titulo;
    private String autor;
    private String genero;
    private int anioPublicacion;
    private int stock;
}


