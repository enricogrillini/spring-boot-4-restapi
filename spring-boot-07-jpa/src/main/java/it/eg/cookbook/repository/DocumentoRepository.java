package it.eg.cookbook.repository;


import it.eg.cookbook.error.ApiException;
import it.eg.cookbook.error.ResponseCode;
import it.eg.cookbook.model.entity.DocumentoEntity;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Repository
public interface DocumentoRepository extends JpaRepository<DocumentoEntity, Long>, JpaSpecificationExecutor<DocumentoEntity> {

    default DocumentoEntity findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(() -> new ApiException(ResponseCode.NOT_FOUND, "Documento non trovato"));
    }

    default Page<DocumentoEntity> find(Pageable pageable, String nome, String descrizione, LocalDate dataDa, LocalDate dataA) {
        Specification<DocumentoEntity> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (!StringUtils.isEmpty(nome)) {
                predicates.add(cb.like(cb.lower(root.get("nome")), "%" + nome.toLowerCase() + "%"));
            }

            if (!StringUtils.isEmpty(descrizione)) {
                predicates.add(cb.like(cb.lower(root.get("descrizione")), "%" + descrizione.toLowerCase() + "%"));
            }

            if (dataDa != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("data"), dataDa));
            }

            if (dataA != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("data"), dataA));
            }

            // Unisce tutto con l'operatore AND
            return cb.and(predicates.toArray(new Predicate[0]));
        };

        return findAll(spec, pageable);
    }

}