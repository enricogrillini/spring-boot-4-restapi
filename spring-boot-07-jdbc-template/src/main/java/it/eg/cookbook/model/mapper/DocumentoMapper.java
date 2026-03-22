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

    @Mapping(target = "autori", ignore = true)
    Documento documentoPojoToApi(DocumentoPojo documentoPojo);

    List<Documento> documentoPojoToApi(List<DocumentoPojo> documentoPojoList);

    Autore autorePojoToApi(AutorePojo autorePojo);

    List<Autore> autorePojoToApi(List<AutorePojo> autorePojoList);
}
