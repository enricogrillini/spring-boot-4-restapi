package it.eg.cookbook.repository;


import it.eg.cookbook.common.Query;
import it.eg.cookbook.error.ApiException;
import it.eg.cookbook.error.ResponseCode;
import it.eg.cookbook.model.mapper.DocumentoMapper;
import it.eg.cookbook.model.pojo.DocumentoPojo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DocumentoDao {


    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final DocumentoMapper documentoMapper;

    private final static String SQL = """
            Select d.*
            From documento d
            /*W*/
            Order By Nome
            """;

    public List<DocumentoPojo> find(String nome, String descrizione, LocalDate dataDa, LocalDate dataA) {
        return new Query(SQL)
                .addFilterAndParam("lower(d.nome) like '%' || lower(:nome) || '%'", "nome", nome)
                .addFilterAndParam("lower(d.descrizione) like '%' || lower(:descrizione) || '%'", "descrizione", descrizione)
                .addFilterAndParam("d.Data >= :dataDa", "dataDa", dataDa)
                .addFilterAndParam("d.Data <= :dataA", "dataA", dataA)
                .select(jdbcTemplate, DocumentoPojo.class);
    }

    public DocumentoPojo findByIdOrThrow(Long idDocumento) {
        try {
            return new Query(SQL)
                    .addFilterAndParam("d.id = :idDocumento", "idDocumento", idDocumento)
                    .selectRow(jdbcTemplate, DocumentoPojo.class);
        } catch (IncorrectResultSizeDataAccessException ex) {
            throw new ApiException(ResponseCode.NOT_FOUND, "Documento non trovato");
        }
    }


}
