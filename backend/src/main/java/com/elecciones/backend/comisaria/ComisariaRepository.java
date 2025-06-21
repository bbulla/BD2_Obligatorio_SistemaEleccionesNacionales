package com.elecciones.backend.comisaria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ComisariaRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Comisaria> findAll() {
        String sql = "SELECT id, nombre, direccion FROM COMISARIA";

        return jdbcTemplate.query(sql, new RowMapper<Comisaria>() {
            @Override
            public Comisaria mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Comisaria(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("direccion")
                );
            }
        });
    }
}
