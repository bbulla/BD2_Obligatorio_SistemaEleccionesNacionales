import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useCircuito } from "../context/CircuitoContext";
import { getHabilitadoPorCC } from "../services/votoService";

export default function VerificarVotante() {
    const { circuito } = useCircuito();
    const [cc, setCc] = useState("");
    const [error, setError] = useState("");
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const habilitado = await getHabilitadoPorCC(cc);

            if (!habilitado) {
                setError("La credencial ingresada no corresponde a ningún habilitado.");
                return;
            }

            const esObservado = String(habilitado.idCircuito) !== String(circuito);

            sessionStorage.setItem("votante", JSON.stringify({
                cc,
                nombres: habilitado.nombres,
                apellidos: habilitado.apellidos,
                esObservado
            }));

            navigate("/votar");
        } catch (err) {
            console.error("Error al verificar CC:", err);

            if (err.response?.status === 404) {
                setError("La credencial ingresada no corresponde a ningún habilitado.");
            } else {
                setError("Ocurrió un error al verificar la credencial.");
            }
        }
    };

    return (
        <section className="section has-background-light">
            <div className="container">
                <div className="box" style={{ maxWidth: 500, margin: "auto" }}>
                    <h2 className="title has-text-centered has-text-link">Verificación de Votante</h2>

                    <form onSubmit={handleSubmit}>
                        <div className="field">
                            <label className="label">Credencial Cívica (CC):</label>
                            <div className="control">
                                <input
                                    className="input"
                                    type="text"
                                    value={cc}
                                    onChange={(e) => setCc(e.target.value)}
                                    required
                                />
                            </div>
                        </div>

                        {error && (
                            <div className="notification is-danger is-light has-text-centered">
                                {error}
                            </div>
                        )}

                        <div className="field has-text-centered">
                            <button className="button is-link is-fullwidth">Continuar</button>
                        </div>
                    </form>
                </div>
            </div>
        </section>
    );
}