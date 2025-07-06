import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useCircuito } from "../context/CircuitoContext";

export default function VerificarVotante() {
    const { circuito } = useCircuito();
    const [cc, setCc] = useState("");
    const [ci, setCi] = useState("");
    const [error, setError] = useState("");
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError("");

        try {
            const response = await fetch("http://localhost:8080/api/habilitados/login", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ cc, ci })
            });

            if (!response.ok) {
                throw new Error("Credenciales inválidas");
            }

            const habilitado = await response.json();

            const esObservado = String(habilitado.idCircuito) !== String(circuito);

            sessionStorage.setItem("votante", JSON.stringify({
                cc,
                nombres: habilitado.nombres,
                apellidos: habilitado.apellidos,
                esObservado
            }));

            navigate("/votar");
        } catch (err) {
            console.error("Error al verificar login:", err);
            setError("Credencial o contraseña inválida");
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

                        <div className="field">
                            <label className="label">Contraseña:</label>
                            <div className="control">
                                <input
                                    className="input"
                                    type="password"
                                    value={ci}
                                    onChange={(e) => setCi(e.target.value)}
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
