package it.eg.cookbook.model.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AutorePojo {

    private Long id;
    private String nome;
    private String cognome;

}