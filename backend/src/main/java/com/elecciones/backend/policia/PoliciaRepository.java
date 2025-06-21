package com.elecciones.backend.policia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PoliciaRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Policia> findAll() {
        String sql = "SELECT nro_policia, CC, id_comisaria, id_establecimiento FROM POLICIA";

        return jdbcTemplate.query(sql, new RowMapper<Policia>() {
            @Override
            public Policia mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Policia(
                        rs.getInt("nro_policia"),
                        rs.getString("CC"),
                        rs.getInt("id_comisaria"),
                        rs.getInt("id_establecimiento")
                );
            }
        });
    }
}
