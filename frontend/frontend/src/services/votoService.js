import axios from "axios";

const API = "http://localhost:8080/api";

export const getCircuitos = async () => {
    const res = await axios.get(`${API}/circuitos`);
    return res.data;
};

export const getListas = async () => {
    const res = await axios.get(`${API}/listas`);
    return res.data;
};

export const getHabilitadoPorCC = async (cc) => {
    const res = await axios.get(`${API}/habilitados/${cc}`);
    return res.data;
};

export const registrarVoto = async (data) => {
    const res = await axios.post(`${API}/votos`, data);
    return res.data;
};

// Obtener el idEstablecimiento según idCircuito
export const getEstablecimientoPorCircuito = async (idCircuito) => {
    const res = await axios.get(`${API}/circuitos/${idCircuito}/establecimiento`);
    return res.data; // se espera que esto sea solo el id del establecimiento
};

export const getElecciones = async () => {
    const res = await axios.get(`${API}/elecciones`);
    return res.data;
};

export const cerrarMesa = async (idCircuito) => {
    const res = await axios.post(`${API}/circuitos/${idCircuito}/cerrar`);
    return res.data;
};

export const getResultadosPorCircuito = async (idCircuito) => {
    const res = await axios.get(`${API}/circuitos/${idCircuito}/resultados`);
    return res.data;
};

export const getResultadosDetalladosPorCircuito = async (idCircuito) => {
    const res = await axios.get(`${API}/circuitos/${idCircuito}/resultados/detalle`);
    return res.data;
};

export const getGanadoresPorDepartamento = async (idEleccion) => {
    const res = await axios.get(`${API}/votos/reportes/eleccion/${idEleccion}/ganadores-por-departamento`);
    return res.data;
};

export const getGanadorEleccion = async (idEleccion) => {
    const res = await axios.get(`${API}/votos/reportes/eleccion/${idEleccion}/ganador`);
    return res.data;
};
