package com.elecciones.backend.voto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class VotoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Voto> findAll() {
        String sql = "SELECT id, id_circuito, id_eleccion, es_observado, tipo FROM VOTO";

        return jdbcTemplate.query(sql, new RowMapper<Voto>() {
            @Override
            public Voto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Voto(
                        rs.getInt("id"),
                        rs.getInt("id_circuito"),
                        rs.getInt("id_eleccion"),
                        rs.getBoolean("es_observado"),
                        rs.getString("tipo")
                );
            }
        });
    }
}
