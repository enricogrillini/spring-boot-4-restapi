package it.eg.cookbook.model.mapper;


import it.eg.cookbook.model.Autore;
import it.eg.cookbook.model.Documento;
import it.eg.cookbook.model.DocumentoPage;
import it.eg.cookbook.model.entity.AutoreEntity;
import it.eg.cookbook.model.entity.DocumentoAutoreEntity;
import it.eg.cookbook.model.entity.DocumentoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AutoreMapper {


    DocumentoPage pageEntityToApi(Page<DocumentoEntity> entityPage);

    @Mapping(target = "autori", source = "documentoAutoreList")
    Documento entityToApi(DocumentoEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "documentoAutoreList", ignore = true)
    void updateEntity(@MappingTarget DocumentoEntity entity, Documento apiDto);
    
    Autore autoreEntityToApi(AutoreEntity entity);

    default List<Autore> documentoAutorelistEntityToApi(List<DocumentoAutoreEntity> entityList) {
        if (entityList == null) {
            return new ArrayList<>();
        }

        return entityList.stream()
                .map(da -> autoreEntityToApi(da.getAutore()))
                .sorted(Comparator
                        .comparing(Autore::getNome)
                        .thenComparing(Autore::getCognome))
                .toList();
    }

}
