package it.eg.cookbook.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class DocumentoBean {

    private Long id;
    private String nome;
    private String descrizione;
    private LocalDate data;

}