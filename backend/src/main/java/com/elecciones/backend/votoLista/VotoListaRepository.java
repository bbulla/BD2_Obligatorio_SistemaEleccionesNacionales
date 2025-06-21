package com.elecciones.backend.votoLista;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class VotoListaRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<VotoLista> findAll() {
        String sql = "SELECT id_voto, id_lista FROM VOTO_LISTA";

        return jdbcTemplate.query(sql, new RowMapper<VotoLista>() {
            @Override
            public VotoLista mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new VotoLista(
                        rs.getInt("id_voto"),
                        rs.getInt("id_lista")
                );
            }
        });
    }
}
