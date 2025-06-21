package com.elecciones.backend.partido;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PartidoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Partido> findAll() {
        String sql = "SELECT id, descripcion, direccion FROM PARTIDO";

        return jdbcTemplate.query(sql, new RowMapper<Partido>() {
            @Override
            public Partido mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Partido(
                        rs.getInt("id"),
                        rs.getString("descripcion"),
                        rs.getString("direccion")
                );
            }
        });
    }
}
