package com.elecciones.backend.voto;

import com.elecciones.backend.voto.exceptions.CircuitoCerradoException;
import com.elecciones.backend.voto.exceptions.NoHabilitadoException;
import com.elecciones.backend.voto.exceptions.YaVotoException;
import com.elecciones.backend.voto.reportes.GanadorEleccionDTO;
import com.elecciones.backend.voto.reportes.GanadorPorDepartamentoDTO;
import com.elecciones.backend.voto.reportes.ResultadoDetalleDTO;
import com.elecciones.backend.voto.reportes.VotosPorListaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

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
        String sqlHabilitado = "SELECT id_circuito FROM HABILITADO WHERE cc = ?";
        List<Integer> asignadoList = jdbcTemplate.queryForList(sqlHabilitado, Integer.class, request.cc);

        if (asignadoList.isEmpty()) {
            throw new NoHabilitadoException("El votante no está habilitado.");
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

        // Registrar que este CC votó en esta elección y circuito
        jdbcTemplate.update("INSERT INTO VOTO_REGISTRO (cc, id_eleccion, id_circuito) VALUES (?, ?, ?)",
                request.cc, request.idEleccion, request.idCircuito);
    }

    public List<VotosPorListaDTO> obtenerVotosPorListaEnCircuito(int idCircuito) {
        String sql = """
        SELECT
            l.id AS id_lista,
            l.lema AS nombre_lista,
            p.descripcion AS nombre_partido,
            COUNT(*) AS total_votos
        FROM
            VOTO v
            JOIN VOTO_LISTA vl ON v.id = vl.id_voto
            JOIN LISTA l ON vl.id_lista = l.id
            JOIN PARTIDO p ON l.id_partido = p.id
        WHERE
            v.id_circuito = ?
            AND v.tipo = 'Válido'
            AND v.es_observado = 0
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
        ), idCircuito);
    }

    public GanadorEleccionDTO obtenerGanadorPorEleccion(int idEleccion) {
        String sql = """
            SELECT
                l.id AS id_lista,
                l.lema AS nombre_lista,
                p.descripcion AS nombre_partido,
                COUNT(*) AS total_votos
            FROM
                VOTO v
                JOIN VOTO_LISTA vl ON v.id = vl.id_voto
                JOIN LISTA l ON vl.id_lista = l.id
                JOIN PARTIDO p ON l.id_partido = p.id
            WHERE
                v.id_eleccion = ?
                AND v.tipo = 'Válido'
                AND v.es_observado = 0
            GROUP BY
                l.id, l.lema, p.descripcion
            ORDER BY
                total_votos DESC
            LIMIT 1
        """;

        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new GanadorEleccionDTO(
                rs.getInt("id_lista"),
                rs.getString("nombre_lista"),
                rs.getString("nombre_partido"),
                rs.getInt("total_votos")
        ), idEleccion);
    }

    public List<ResultadoDetalleDTO> obtenerResultadosDetalladosPorCircuito(int idCircuito) {
        String totalSql = "SELECT COUNT(*) FROM VOTO WHERE id_circuito = ?";
        int total = jdbcTemplate.queryForObject(totalSql, Integer.class, idCircuito);

        if (total == 0) return List.of();

        List<ResultadoDetalleDTO> resultados = new ArrayList<>();

        // 1. Votos válidos por lista
        String sqlListas = """
        SELECT
            l.nro_lista,
            l.lema,
            p.descripcion AS partido,
            COUNT(*) AS cantidad
        FROM
            VOTO v
            JOIN VOTO_LISTA vl ON v.id = vl.id_voto
            JOIN LISTA l ON vl.id_lista = l.id
            JOIN PARTIDO p ON l.id_partido = p.id
        WHERE
            v.id_circuito = ?
            AND v.tipo = 'Válido'
        GROUP BY
            l.nro_lista, l.lema, p.descripcion
    """;

        jdbcTemplate.query(sqlListas, rs -> {
            String nombre = "Lista " + rs.getInt("nro_lista") + " - " + rs.getString("partido");
            int cantidad = rs.getInt("cantidad");
            double porcentaje = (cantidad * 100.0) / total;
            resultados.add(new ResultadoDetalleDTO(nombre, cantidad, porcentaje));
        }, idCircuito);

        // 2. Votos en blanco
        String blancosSql = "SELECT COUNT(*) FROM VOTO WHERE id_circuito = ? AND tipo = 'Blanco'";
        int blancos = jdbcTemplate.queryForObject(blancosSql, Integer.class, idCircuito);
        if (blancos > 0) {
            resultados.add(new ResultadoDetalleDTO("En Blanco", blancos, (blancos * 100.0) / total));
        }

        // 3. Votos anulados
        String anuladosSql = "SELECT COUNT(*) FROM VOTO WHERE id_circuito = ? AND tipo = 'Anulado'";
        int anulados = jdbcTemplate.queryForObject(anuladosSql, Integer.class, idCircuito);
        if (anulados > 0) {
            resultados.add(new ResultadoDetalleDTO("Anulado", anulados, (anulados * 100.0) / total));
        }

        return resultados;
    }

    public List<GanadorPorDepartamentoDTO> obtenerGanadoresPorDepartamento(int idEleccion) {
        String sql = """
        SELECT
            d.nombre AS departamento,
            l.nro_lista AS lista,
            p.descripcion AS partido,
            COUNT(*) AS votos
        FROM VOTO v
            JOIN CIRCUITO c ON v.id_circuito = c.id
            JOIN ESTABLECIMIENTO e ON c.id_establecimiento = e.id
            JOIN LOCALIDAD loc ON e.id_localidad = loc.id
            JOIN DEPARTAMENTO d ON loc.id_departamento = d.id
            JOIN VOTO_LISTA vl ON v.id = vl.id_voto
            JOIN LISTA l ON vl.id_lista = l.id
            JOIN PARTIDO p ON l.id_partido = p.id
        WHERE
            v.id_eleccion = ?
            AND v.tipo = 'Válido'
            AND v.es_observado = 0
        GROUP BY d.nombre, l.nro_lista, p.descripcion
    """;

        Map<String, GanadorPorDepartamentoDTO> maxPorDepto = new HashMap<>();

        jdbcTemplate.query(sql, rs -> {
            String departamento = rs.getString("departamento");
            String lista = rs.getString("lista");
            String partido = rs.getString("partido");
            int votos = rs.getInt("votos");

            GanadorPorDepartamentoDTO actual = maxPorDepto.get(departamento);
            if (actual == null || votos > actual.getVotos()) {
                maxPorDepto.put(departamento, new GanadorPorDepartamentoDTO(departamento, lista, partido, votos));
            }
        }, idEleccion);

        return new ArrayList<>(maxPorDepto.values());
    }

}