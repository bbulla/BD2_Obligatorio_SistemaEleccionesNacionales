package com.elecciones.backend.papeleta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PapeletaRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Papeleta> findAll() {
        String sql = "SELECT id, descripcion FROM PAPELETA";

        return jdbcTemplate.query(sql, new RowMapper<Papeleta>() {
            @Override
            public Papeleta mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Papeleta(
                        rs.getInt("id"),
                        rs.getString("descripcion")
                );
            }
        });
    }
}
