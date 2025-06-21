package com.elecciones.backend.eleccion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class EleccionRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Eleccion> findAll() {
        String sql = "SELECT id, tipo, fecha, descripcion FROM ELECCION";

        return jdbcTemplate.query(sql, new RowMapper<Eleccion>() {
            @Override
            public Eleccion mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Eleccion(
                        rs.getInt("id"),
                        rs.getString("tipo"),
                        rs.getObject("fecha", LocalDate.class),
                        rs.getString("descripcion")
                );
            }
        });
    }
}
