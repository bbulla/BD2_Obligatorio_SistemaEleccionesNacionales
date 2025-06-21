package com.elecciones.backend.miembroMesa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MiembroMesaRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<MiembroMesa> findAll() {
        String sql = "SELECT CC, organismo, rol, id_circuito FROM MIEMBRO_MESA";

        return jdbcTemplate.query(sql, new RowMapper<MiembroMesa>() {
            @Override
            public MiembroMesa mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new MiembroMesa(
                        rs.getString("CC"),
                        rs.getString("organismo"),
                        rs.getString("rol"),
                        rs.getInt("id_circuito")
                );
            }
        });
    }
}
