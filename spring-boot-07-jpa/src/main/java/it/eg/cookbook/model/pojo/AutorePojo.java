package it.eg.cookbook.model.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class DocumentoPojo {

    private Long id;
    private String nome;
    private String descrizione;
    private LocalDate data;

}