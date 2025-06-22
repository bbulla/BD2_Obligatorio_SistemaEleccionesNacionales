package com.elecciones.backend.circuito;

import com.elecciones.backend.circuito.exceptions.CircuitoNoEncontradoException;
import com.elecciones.backend.voto.exceptions.CircuitoCerradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CircuitoService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CircuitoRepository circuitoRepository;

    public List<Circuito> getAllCircuitos() {
        return circuitoRepository.findAll();
    }

    public void cerrarCircuito(int idCircuito) {
        String checkSql = "SELECT cerrado FROM CIRCUITO WHERE id = ?";
        Boolean cerrado = jdbcTemplate.queryForObject(checkSql, Boolean.class, idCircuito);

        if (cerrado == null) {
            throw new CircuitoNoEncontradoException("El circuito no existe.");
        }

        if (cerrado) {
            throw new CircuitoCerradoException("El circuito ya fue cerrado.");
        }

        String updateSql = "UPDATE CIRCUITO SET cerrado = 1 WHERE id = ?";
        jdbcTemplate.update(updateSql, idCircuito);
    }

    public Integer obtenerEstablecimientoPorCircuito(int id) {
        String sql = "SELECT id_establecimiento FROM CIRCUITO WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, Integer.class, id);
        } catch (Exception e) {
            return null;
        }
    }

}