package com.elecciones.backend.habilitado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class HabilitadoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Habilitado> findAll() {
        String sql = "SELECT cc, ci, nombres, apellidos, f_nacimiento, id_circuito FROM HABILITADO";

        return jdbcTemplate.query(sql, new RowMapper<Habilitado>() {
            @Override
            public Habilitado mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Habilitado(
                        rs.getString("cc"),
                        rs.getString("ci"),
                        rs.getString("nombres"),
                        rs.getString("apellidos"),
                        rs.getObject("f_nacimiento", LocalDate.class),
                        rs.getInt("id_circuito")
                );
            }
        });
    }

    public Habilitado findByCc(String cc) {
        String sql = "SELECT cc, ci, nombres, apellidos, f_nacimiento, id_circuito FROM HABILITADO WHERE cc = ?";
        List<Habilitado> result = jdbcTemplate.query(sql, new Object[]{cc}, (rs, rowNum) -> new Habilitado(
                rs.getString("cc"),
                rs.getString("ci"),
                rs.getString("nombres"),
                rs.getString("apellidos"),
                rs.getDate("f_nacimiento").toLocalDate(),
                rs.getInt("id_circuito")
        ));
        return result.isEmpty() ? null : result.get(0);
    }
}