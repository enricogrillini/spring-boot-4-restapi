package it.eg.cookbook.repository;


import it.eg.cookbook.model.pojo.DocumentoPojo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DocumentoRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<DocumentoPojo> findAll() {
        String sql = "SELECT * FROM documento";
        List<DocumentoPojo> result = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(DocumentoPojo.class));



        return result;
    }

}