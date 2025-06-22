package com.elecciones.backend.circuito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public class CircuitoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Circuito> findAll() {
        String sql = "SELECT id, id_establecimiento, es_accesible, cerrado FROM CIRCUITO";

        return jdbcTemplate.query(sql, new RowMapper<Circuito>() {
            @Override
            public Circuito mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Circuito(
                        rs.getInt("id"),
                        rs.getInt("id_establecimiento"),
                        rs.getBoolean("es_accesible"),
                        rs.getBoolean("cerrado")
                );
            }
        });
    }
}