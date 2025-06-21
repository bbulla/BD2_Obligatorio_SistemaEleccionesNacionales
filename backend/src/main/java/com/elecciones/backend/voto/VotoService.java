package com.elecciones.backend.voto;

import com.elecciones.backend.voto.exceptions.CircuitoCerradoException;
import com.elecciones.backend.voto.exceptions.NoHabilitadoException;
import com.elecciones.backend.voto.exceptions.YaVotoException;
import com.elecciones.backend.voto.reportes.VotosPorListaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VotoService {

    @Autowired
    private VotoRepository votoRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Voto> getAllVotos() {
        return votoRepository.findAll();
    }

    public void registrarVoto(VotoRequest request) {
        // Verificar si ya votó en esta elección
        String checkSql = "SELECT COUNT(*) FROM VOTO_REGISTRO WHERE cc = ? AND id_eleccion = ?";
        Integer count = jdbcTemplate.queryForObject(checkSql, Integer.class, request.cc, request.idEleccion);

        if (count != null && count > 0) {
            throw new YaVotoException("Este ciudadano ya emitió su voto en esta elección.");
        }

        // Verificar si el circuito está cerrado
        String checkCerrado = "SELECT cerrado FROM CIRCUITO WHERE id = ?";
        Boolean cerrado = jdbcTemplate.queryForObject(checkCerrado, Boolean.class, request.idCircuito);

        if (Boolean.TRUE.equals(cerrado)) {
            throw new CircuitoCerradoException("El circuito ya fue cerrado. No se puede registrar el voto.");
        }

        // validar si esta habilitado para votar
        String sqlHabilitado = "SELECT id_circuito FROM HABILITADO WHERE cc = ? AND id_eleccion = ?";
        List<Integer> asignadoList = jdbcTemplate.queryForList(sqlHabilitado, Integer.class, request.cc, request.idEleccion);

        if (asignadoList.isEmpty()) {
            throw new NoHabilitadoException("El votante no está habilitado para esta elección.");
        }

        // validar si es observado o no
        Integer circuitoAsignado = asignadoList.get(0);
        boolean esObservado = !circuitoAsignado.equals(request.idCircuito);


        // Insertar en VOTO
        String sqlVoto = "INSERT INTO VOTO (id_circuito, id_eleccion, es_observado, tipo) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sqlVoto,
                request.idCircuito,
                request.idEleccion,
                esObservado,
                request.tipo
        );

        Integer idGenerado = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        // Insertar en VOTO_LISTA si hay lista
        if (request.idLista != null) {
            jdbcTemplate.update("INSERT INTO VOTO_LISTA (id_voto, id_lista) VALUES (?, ?)", idGenerado, request.idLista);
        }

        // Insertar en VOTO_PAPELETA si hay papeleta
        if (request.idPapeleta != null) {
            jdbcTemplate.update("INSERT INTO VOTO_PAPELETA (id_voto, id_papeleta) VALUES (?, ?)", idGenerado, request.idPapeleta);
        }

        // Registrar que este CC votó en esta elección
        jdbcTemplate.update("INSERT INTO VOTO_REGISTRO (cc, id_eleccion) VALUES (?, ?)", request.cc, request.idEleccion);
    }

    public List<VotosPorListaDTO> obtenerVotosPorListaEnCircuito(int idCircuito, int idEstablecimiento) {
        String sql = """
        
                        SELECT
            l.id AS id_lista,
            l.lema AS nombre_lista,
            p.descripcion AS nombre_partido,
            COUNT(*) AS total_votos
                        FROM
            VOTO v
            JOIN CIRCUITO c ON v.id_circuito = c.id
            JOIN VOTO_LISTA vl ON v.id = vl.
                id_voto
            JOIN LISTA l ON vl
                .id_lista = l.id
            JOIN PARTIDO p ON l.
                id_partido = p.id
                        WHERE
            v.id_circuito =
                ?
            AND c.
                id_establecimiento = ?
            AND v.tipo =
                'Válido'
            AND v.
                es_observado = 0
        GROUP BY
            l.id, l.lema, p.descripcion
        ORDER BY
            total_votos DESC;
        """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> new VotosPorListaDTO(
                rs.getInt("id_lista"),
                rs.getString("nombre_lista"),
                rs.getString("nombre_partido"),
                rs.getInt("total_votos")
        ), idCircuito, idEstablecimiento);
    }

}