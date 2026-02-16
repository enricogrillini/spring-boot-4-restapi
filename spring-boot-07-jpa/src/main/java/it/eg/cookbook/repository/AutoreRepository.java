package it.eg.cookbook.repository;


import it.eg.cookbook.error.ApiException;
import it.eg.cookbook.error.ResponseCode;
import it.eg.cookbook.model.entity.AutoreEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AutoreRepository extends CrudRepository<AutoreEntity, Long> {

    Optional<AutoreEntity> findByNomeAndCognome(String nome, String cognome);

    default AutoreEntity findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(() -> new ApiException(ResponseCode.NOT_FOUND, "Autore non trovato"));
    }

    default AutoreEntity findByNomeAndCognomeOrThrow(String nome, String cognome) {
        return findByNomeAndCognome(nome, cognome).orElseThrow(() -> new ApiException(ResponseCode.NOT_FOUND, "Autore non trovato"));
    }

}