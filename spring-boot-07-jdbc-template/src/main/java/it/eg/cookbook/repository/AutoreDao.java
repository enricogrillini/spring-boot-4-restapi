package it.eg.cookbook.repository;


import it.eg.cookbook.common.Query;
import it.eg.cookbook.model.pojo.AutorePojo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AutoreDao {


    private final NamedParameterJdbcTemplate jdbcTemplate;

    private final static String SQL = """
            Select a.*
            From autore a
			/*W*/
            Order By Cognome, Nome
            """;

    public List<AutorePojo> findAll(Long idDocumento) {
        return new Query(SQL)
                .addFilterAndParam("a.id in (select da.id_autore from documento_autore da where da.id_documento = :idDocumento)", "idDocumento", idDocumento)
                .select(jdbcTemplate, AutorePojo.class);
//
//        List<DocumentoPojo> result = jdbcTemplate.query(SQL, new BeanPropertyRowMapper<>(DocumentoPojo.class));
//
//        for (DocumentoPojo documentoBean : result) {
//            String sql = "SELECT * FROM autore ";
//        }
//
//        return result;
    }

}
