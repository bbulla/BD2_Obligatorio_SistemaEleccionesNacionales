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
        String sql = "SELECT CC, CI, nombres, apellidos, f_nacimiento, fecha_voto, posicion, id_circuito, id_partido FROM HABILITADO";

        return jdbcTemplate.query(sql, new RowMapper<Habilitado>() {
            @Override
            public Habilitado mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Habilitado(
                        rs.getString("CC"),
                        rs.getString("CI"),
                        rs.getString("nombres"),
                        rs.getString("apellidos"),
                        rs.getObject("f_nacimiento", LocalDate.class),
                        rs.getObject("fecha_voto", LocalDate.class),
                        (Integer) rs.getObject("posicion"),
                        rs.getInt("id_circuito"),
                        rs.getInt("id_partido")
                );
            }
        });
    }
}