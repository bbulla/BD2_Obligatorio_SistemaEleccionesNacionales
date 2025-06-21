package com.elecciones.backend.localidad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class LocalidadRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Localidad> findAll() {
        String sql = "SELECT id, nombre, id_departamento FROM LOCALIDAD";

        return jdbcTemplate.query(sql, new RowMapper<Localidad>() {
            @Override
            public Localidad mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Localidad(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getInt("id_departamento")
                );
            }
        });
    }
}