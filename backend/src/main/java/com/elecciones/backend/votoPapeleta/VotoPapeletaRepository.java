package com.elecciones.backend.votoPapeleta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class VotoPapeletaRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<VotoPapeleta> findAll() {
        String sql = "SELECT id_voto, id_papeleta FROM VOTO_PAPELETA";

        return jdbcTemplate.query(sql, new RowMapper<VotoPapeleta>() {
            @Override
            public VotoPapeleta mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new VotoPapeleta(
                        rs.getInt("id_voto"),
                        rs.getInt("id_papeleta")
                );
            }
        });
    }
}
