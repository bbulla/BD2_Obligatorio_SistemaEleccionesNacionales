package com.elecciones.backend.repository;

import com.elecciones.backend.model.Departamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class DepartamentoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Departamento> findAll() {
        String sql = "SELECT id, nombre FROM DEPARTAMENTO";

        return jdbcTemplate.query(sql, new RowMapper<Departamento>() {
            @Override
            public Departamento mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Departamento(
                        rs.getInt("id"),
                        rs.getString("nombre")
                );
            }
        });
    }
}