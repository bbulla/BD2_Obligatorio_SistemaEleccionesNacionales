package com.elecciones.backend.repository;

import com.elecciones.backend.model.Establecimiento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EstablecimientoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Establecimiento> findAll() {
        String sql = "SELECT id, nombre, tipo, id_localidad FROM ESTABLECIMIENTO";

        return jdbcTemplate.query(sql, new RowMapper<Establecimiento>() {
            @Override
            public Establecimiento mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Establecimiento(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("tipo"),
                        rs.getInt("id_localidad")
                );
            }
        });
    }
}