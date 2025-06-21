package com.elecciones.backend.lista;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ListaRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Lista> findAll() {
        String sql = "SELECT id, lema, nro_lista, id_eleccion FROM LISTA";

        return jdbcTemplate.query(sql, new RowMapper<Lista>() {
            @Override
            public Lista mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Lista(
                        rs.getInt("id"),
                        rs.getString("lema"),
                        rs.getInt("nro_lista"),
                        rs.getInt("id_eleccion")
                );
            }
        });
    }
}
