import React, { useEffect, useState } from "react";
import { useCircuito } from "../context/CircuitoContext";
import { useNavigate } from "react-router-dom";
import {
    getEstablecimientoPorCircuito,
    cerrarMesa,
    getResultadosDetalladosPorCircuito,
    getGanadoresPorDepartamento,
    getGanadorEleccion
} from "../services/votoService";

export default function MenuPrincipal() {
    const { circuito, eleccion } = useCircuito();
    const [establecimiento, setEstablecimiento] = useState(null);
    const [mostrarPin, setMostrarPin] = useState(false);
    const [pinIngresado, setPinIngresado] = useState("");
    const [esPresidente, setEsPresidente] = useState(false);
    const [mensaje, setMensaje] = useState("");
    const [resultados, setResultados] = useState([]);
    const [ganadores, setGanadores] = useState([]);
    const [ganadorNacional, setGanadorNacional] = useState(null);
    const [mesaCerrada, setMesaCerrada] = useState(false);
    const navigate = useNavigate();

    useEffect(() => {
        if (!circuito) {
            navigate("/");
            return;
        }

        getEstablecimientoPorCircuito(circuito).then((res) => {
            setEstablecimiento(res);
        });
    }, [circuito, navigate]);

    const handleVerificacionPin = () => {
        if (pinIngresado === "1234") {
            setEsPresidente(true);
            setMensaje("");
        } else {
            setMensaje("❌ PIN incorrecto.");
        }
    };

    const handleCerrarMesa = async () => {
        try {
            await cerrarMesa(circuito);
            setMensaje("✅ Mesa cerrada correctamente.");
            setMesaCerrada(true);
        } catch (err) {
            const msg = err.response?.data || "Error al cerrar la mesa.";
            setMensaje(`❌ ${msg}`);

            if (msg.includes("ya fue cerrado")) {
                setMesaCerrada(true);
            }
        }
    };

    const handleVerResultados = async () => {
        try {
            const resultadosData = await getResultadosDetalladosPorCircuito(circuito);
            setResultados(resultadosData);

            const ganadoresData = await getGanadoresPorDepartamento(eleccion || 1);
            setGanadores(ganadoresData);

            const ganador = await getGanadorEleccion(eleccion || 1);
            setGanadorNacional(ganador);
        } catch (err) {
            setMensaje("❌ No se pudieron cargar los resultados.");
        }
    };

    if (!circuito || !establecimiento) return <p className="has-text-centered mt-5">Cargando...</p>;

    return (
        <section className="section has-background-light">
            <div className="container">
                <div className="box" style={{ maxWidth: 600, margin: "auto" }}>
                    <h2 className="title has-text-centered has-text-link">Menú Principal</h2>

                    <div className="content">
                        <p><strong>Circuito:</strong> {circuito}</p>
                        <p><strong>Establecimiento:</strong> {establecimiento}</p>
                    </div>

                    <div className="field has-text-centered">
                        <button
                            className="button is-link is-large is-fullwidth"
                            onClick={() => navigate("/verificar")}
                        >
                            Votar
                        </button>
                    </div>

                    <hr />

                    {!esPresidente && !mostrarPin && (
                        <div className="field has-text-centered">
                            <button
                                className="button is-warning is-light is-fullwidth"
                                onClick={() => setMostrarPin(true)}
                            >
                                Soy presidente de mesa
                            </button>
                        </div>
                    )}

                    {mostrarPin && !esPresidente && (
                        <>
                            <div className="field">
                                <label className="label">Ingrese PIN:</label>
                                <div className="control">
                                    <input
                                        type="password"
                                        className="input"
                                        value={pinIngresado}
                                        onChange={(e) => setPinIngresado(e.target.value)}
                                    />
                                </div>
                            </div>
                            <div className="field has-text-centered">
                                <button
                                    className="button is-info is-fullwidth"
                                    onClick={handleVerificacionPin}
                                >
                                    Verificar PIN
                                </button>
                            </div>
                        </>
                    )}

                    {esPresidente && (
                        <div className="field has-text-centered">
                            <button
                                className="button is-danger is-fullwidth mb-2"
                                onClick={handleCerrarMesa}
                            >
                                Cerrar mesa
                            </button>

                            {mesaCerrada && (
                                <button
                                    className="button is-success is-fullwidth"
                                    onClick={handleVerResultados}
                                >
                                    Ver resultados
                                </button>
                            )}
                        </div>
                    )}

                    {mensaje && (
                        <div className="notification mt-4 is-light has-text-centered">
                            {mensaje}
                        </div>
                    )}

                    {resultados.length > 0 && (
                        <div className="box mt-4">
                            <h3 className="title is-5 has-text-centered">Resultados por Lista</h3>
                            <table className="table is-fullwidth is-bordered is-striped">
                                <thead>
                                <tr>
                                    <th>Tipo</th>
                                    <th>Votos</th>
                                    <th>%</th>
                                </tr>
                                </thead>
                                <tbody>
                                {resultados.map((r, index) => (
                                    <tr key={index}>
                                        <td>{r.tipo}</td>
                                        <td>{r.cantidad}</td>
                                        <td>{r.porcentaje.toFixed(2)}%</td>
                                    </tr>
                                ))}
                                </tbody>
                            </table>
                        </div>
                    )}

                    {ganadores.length > 0 && (
                        <div className="box mt-4">
                            <h3 className="title is-5 has-text-centered">Ganador por Departamento</h3>
                            <table className="table is-fullwidth is-bordered is-striped">
                                <thead>
                                <tr>
                                    <th>Departamento</th>
                                    <th>Lista</th>
                                    <th>Partido</th>
                                    <th>Votos</th>
                                </tr>
                                </thead>
                                <tbody>
                                {ganadores.map((g, index) => (
                                    <tr key={index}>
                                        <td>{g.departamento}</td>
                                        <td>{g.lista}</td>
                                        <td>{g.partido}</td>
                                        <td>{g.votos}</td>
                                    </tr>
                                ))}
                                </tbody>
                            </table>
                        </div>
                    )}

                    {ganadorNacional && (
                        <div className="box mt-4">
                            <h3 className="title is-5 has-text-centered">Ganador Nacional</h3>
                            <div className="content has-text-centered">
                                <p><strong>Lista:</strong> {ganadorNacional.nombreLista}</p>
                                <p><strong>Partido:</strong> {ganadorNacional.nombrePartido}</p>
                                <p><strong>Total de votos:</strong> {ganadorNacional.totalVotos}</p>
                            </div>
                        </div>
                    )}
                </div>
            </div>
        </section>
    );
}