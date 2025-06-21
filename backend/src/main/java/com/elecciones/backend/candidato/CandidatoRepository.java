package com.elecciones.backend.candidato;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CandidatoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Candidato> findAll() {
        String sql = "SELECT CC, nro_posicion, rol FROM CANDIDATO";

        return jdbcTemplate.query(sql, new RowMapper<Candidato>() {
            @Override
            public Candidato mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Candidato(
                        rs.getString("CC"),
                        rs.getInt("nro_posicion"),
                        rs.getString("rol")
                );
            }
        });
    }
}
