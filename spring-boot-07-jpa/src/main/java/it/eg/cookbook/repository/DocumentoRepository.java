package it.eg.cookbook.repository;


import it.eg.cookbook.error.ApiException;
import it.eg.cookbook.error.ResponseCode;
import it.eg.cookbook.model.entity.DocumentoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentoRepository extends JpaRepository<DocumentoEntity, Long> {

    default DocumentoEntity findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(() -> new ApiException(ResponseCode.NOT_FOUND, "Documento non trovato"));
    }

    @Query("""
            SELECT d
            FROM DocumentoEntity d
            WHERE (:nome is null Or d.nome ILIKE %:nome% ) and
                  (:descrizione is null Or d.descrizione ILIKE %:descrizione% )
            ORDER BY d.nome
            """)
    Page<DocumentoEntity> find(Pageable pageble,
                               @Param("nome") String nome,
                               @Param("descrizione") String descrizione);

}