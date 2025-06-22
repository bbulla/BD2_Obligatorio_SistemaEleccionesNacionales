import React, { useEffect, useState } from "react";
import { getCircuitos, getElecciones } from "../services/votoService";
import { useCircuito } from "../context/CircuitoContext";
import { useNavigate } from "react-router-dom";

export default function SeleccionarCircuito() {
    const [circuitos, setCircuitos] = useState([]);
    const [elecciones, setElecciones] = useState([]);
    const [circuitoSeleccionado, setCircuitoSeleccionado] = useState("");
    const [eleccionSeleccionada, setEleccionSeleccionada] = useState("");
    const { setCircuito, setEleccion } = useCircuito();
    const navigate = useNavigate();

    useEffect(() => {
        getCircuitos().then(setCircuitos);
        getElecciones().then(setElecciones);
    }, []);

    const handleSubmit = (e) => {
        e.preventDefault();
        if (circuitoSeleccionado && eleccionSeleccionada) {
            setCircuito(circuitoSeleccionado);
            setEleccion(eleccionSeleccionada);
            navigate("/menu");
        }
    };

    return (
        <section className="section has-background-light">
            <div className="container">
                <div className="box" style={{ maxWidth: 500, margin: "auto" }}>
                    <h1 className="title has-text-centered has-text-link">
                        Sistema de Elecciones Nacionales
                    </h1>
                    <form onSubmit={handleSubmit}>
                        <div className="field">
                            <label className="label">Seleccionar circuito</label>
                            <div className="control">
                                <div className="select is-fullwidth">
                                    <select
                                        value={circuitoSeleccionado}
                                        onChange={(e) => setCircuitoSeleccionado(e.target.value)}
                                        required
                                    >
                                        <option value="">Seleccione un circuito</option>
                                        {circuitos.map((c) => (
                                            <option key={c.id} value={c.id}>
                                                {c.id}
                                            </option>
                                        ))}
                                    </select>
                                </div>
                            </div>
                        </div>

                        <div className="field">
                            <label className="label">Seleccionar elección</label>
                            <div className="control">
                                <div className="select is-fullwidth">
                                    <select
                                        value={eleccionSeleccionada}
                                        onChange={(e) => setEleccionSeleccionada(e.target.value)}
                                        required
                                    >
                                        <option value="">Seleccione una elección</option>
                                        {elecciones.map((e) => (
                                            <option key={e.id} value={e.id}>
                                                {e.descripcion} ({e.tipo})
                                            </option>
                                        ))}
                                    </select>
                                </div>
                            </div>
                        </div>

                        <div className="field has-text-centered">
                            <button className="button is-link is-fullwidth">Continuar</button>
                        </div>
                    </form>
                </div>
            </div>
        </section>
    );
}
