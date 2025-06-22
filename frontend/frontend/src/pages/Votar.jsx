import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useCircuito } from "../context/CircuitoContext";
import { getListas, registrarVoto } from "../services/votoService";

export default function Votar() {
    const navigate = useNavigate();
    const [votante, setVotante] = useState(null);
    const [listas, setListas] = useState([]);
    const [tipo, setTipo] = useState("Válido");
    const [idLista, setIdLista] = useState("");
    const [mensaje, setMensaje] = useState("");
    const { circuito, eleccion } = useCircuito();

    useEffect(() => {
        const data = sessionStorage.getItem("votante");
        if (!data || !circuito) {
            navigate("/");
            return;
        }

        setVotante(JSON.parse(data));
        getListas().then(setListas);
    }, [navigate, circuito]);

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            await registrarVoto({
                cc: votante.cc,
                idCircuito: circuito,
                idEleccion: eleccion,
                tipo,
                idLista: tipo === "Válido" ? idLista : null
            });

            setMensaje("✅ Voto registrado correctamente.");
            sessionStorage.removeItem("votante");

            setTimeout(() => navigate("/menu"), 2000);
        } catch (err) {
            console.error("Error al registrar voto:", err);
            const errorMsg = err.response?.data;

            if (
                err.response?.status === 400 &&
                typeof errorMsg === "string" &&
                errorMsg.includes("El circuito ya fue cerrado")
            ) {
                setMensaje("⛔ No se puede registrar el voto porque la mesa está cerrada.");
            } else {
                setMensaje("❌ Error al registrar el voto.");
            }
        }
    };

    if (!votante) return <p className="has-text-centered mt-5">Cargando votante...</p>;

    return (
        <section className="section has-background-light">
            <div className="container">
                <div className="box" style={{ maxWidth: 600, margin: "auto" }}>
                    <h2 className="title has-text-centered has-text-link">Registro de Voto</h2>

                    <div className="content">
                        <p><strong>CC:</strong> {votante.cc}</p>
                        <p><strong>Nombre:</strong> {votante.nombres}</p>
                        <p><strong>Apellido:</strong> {votante.apellidos}</p>
                        <p><strong>Voto observado:</strong> {votante.esObservado ? "Sí" : "No"}</p>
                    </div>

                    <form onSubmit={handleSubmit}>
                        <div className="field">
                            <label className="label">Tipo de voto:</label>
                            <div className="control">
                                <div className="select is-fullwidth">
                                    <select value={tipo} onChange={(e) => setTipo(e.target.value)} required>
                                        <option value="Válido">Válido</option>
                                        <option value="Blanco">Blanco</option>
                                        <option value="Anulado">Anulado</option>
                                    </select>
                                </div>
                            </div>
                        </div>

                        {tipo === "Válido" && (
                            <div className="field">
                                <label className="label">Lista:</label>
                                <div className="control">
                                    <div className="select is-fullwidth">
                                        <select value={idLista} onChange={(e) => setIdLista(e.target.value)} required>
                                            <option value="">Seleccione una lista</option>
                                            {listas.map((l) => (
                                                <option key={l.id} value={l.id}>
                                                    {l.nro_lista} - {l.lema}
                                                </option>
                                            ))}
                                        </select>
                                    </div>
                                </div>
                            </div>
                        )}

                        <div className="field has-text-centered">
                            <button className="button is-link is-fullwidth">Registrar voto</button>
                        </div>
                    </form>

                    {mensaje && (
                        <div className="notification mt-4 is-light has-text-centered">
                            {mensaje}
                        </div>
                    )}
                </div>
            </div>
        </section>
    );
}
