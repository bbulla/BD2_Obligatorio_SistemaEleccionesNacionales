package com.elecciones.backend.eleccion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EleccionService {

    @Autowired
    private EleccionRepository eleccionRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Eleccion> getAllElecciones() {
        return eleccionRepository.findAll();
    }

    public void saveEleccion(EleccionRequest request) {
        String sql = "INSERT INTO ELECCION (descripcion, tipo, fecha) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, request.getDescripcion(), request.getTipo(), request.getFecha());
    }

}
