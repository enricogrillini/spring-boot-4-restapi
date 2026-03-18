package it.eg.cookbook.model.mapper;


import it.eg.cookbook.model.Autore;
import it.eg.cookbook.model.Documento;
import it.eg.cookbook.model.pojo.AutorePojo;
import it.eg.cookbook.model.pojo.DocumentoPojo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DocumentoMapper {

    @Mapping(target = "autori", source = "autorePojoList")
    Documento pojoToApi(DocumentoPojo documentoPojo, List<AutorePojo> autorePojoList);

    Autore pojoToApi(AutorePojo autorePojo);

    List<Autore> pojoToApi(List<AutorePojo> autorePojoList);
}
