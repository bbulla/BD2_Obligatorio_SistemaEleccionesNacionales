package com.elecciones.backend.circuito;

import com.elecciones.backend.circuito.exceptions.CircuitoNoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class CircuitoService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void cerrarCircuito(int idCircuito, int idEstablecimiento) {
        String sql = "UPDATE CIRCUITO SET cerrado = 1 WHERE id = ? AND id_establecimiento = ?";
        int filas = jdbcTemplate.update(sql, idCircuito, idEstablecimiento);

        if (filas == 0) {
            throw new CircuitoNoEncontradoException("El circuito no existe o no pertenece al establecimiento indicado.");
        }
    }
}