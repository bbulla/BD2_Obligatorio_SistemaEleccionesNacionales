import React, { useState } from "react";

export default function Admin() {
    const [clave, setClave] = useState("");
    const [esAdmin, setEsAdmin] = useState(false);
    const [mensaje, setMensaje] = useState("");
    const [formVisible, setFormVisible] = useState(null);

    // Estados para cargar elección
    const [descripcion, setDescripcion] = useState("");
    const [tipo, setTipo] = useState("");
    const [fecha, setFecha] = useState("");
    const [mensajeEleccion, setMensajeEleccion] = useState("");

    const manejarVerificacion = () => {
        if (clave === "1234") {
            setEsAdmin(true);
            setMensaje("");
        } else {
            setMensaje("❌ Clave incorrecta");
        }
    };

    const manejarCargarEleccion = async () => {
        try {
            const res = await fetch("http://localhost:8080/api/elecciones", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({
                    descripcion,
                    tipo,
                    fecha,
                }),
            });

            if (!res.ok) throw new Error("Error al cargar la elección");

            setMensajeEleccion("✅ Elección cargada correctamente.");
            setDescripcion("");
            setTipo("");
            setFecha("");
        } catch (err) {
            console.error(err);
            setMensajeEleccion("❌ Error al cargar elección.");
        }
    };

    return (
        <section className="section has-background-light">
            <div className="container">
                <div className="box" style={{ maxWidth: 600, margin: "auto" }}>
                    <h2 className="title has-text-centered has-text-link">Panel Administrativo</h2>

                    {!esAdmin ? (
                        <>
                            <div className="field">
                                <label className="label">Clave de acceso:</label>
                                <div className="control">
                                    <input
                                        type="password"
                                        className="input"
                                        value={clave}
                                        onChange={(e) => setClave(e.target.value)}
                                    />
                                </div>
                            </div>
                            <div className="field has-text-centered">
                                <button className="button is-info is-fullwidth" onClick={manejarVerificacion}>
                                    Ingresar
                                </button>
                            </div>
                            {mensaje && (
                                <div className="notification is-danger is-light has-text-centered">
                                    {mensaje}
                                </div>
                            )}
                        </>
                    ) : (
                        <>
                            <div className="buttons is-centered mb-4">
                                <button className="button is-info" onClick={() => setFormVisible("eleccion")}>Cargar elección</button>
                                <button className="button is-info" onClick={() => setFormVisible("circuito")}>Cargar circuito</button>
                                <button className="button is-info" onClick={() => setFormVisible("lista")}>Cargar lista</button>
                                <button className="button is-info" onClick={() => setFormVisible("partido")}>Cargar partido</button>
                                <button className="button is-info" onClick={() => setFormVisible("candidato")}>Cargar candidato</button>
                                <button className="button is-info" onClick={() => setFormVisible("votante")}>Cargar votante</button>
                            </div>

                            {formVisible === "eleccion" && (
                                <div className="box">
                                    <h3 className="title is-5">Cargar elección</h3>

                                    <div className="field">
                                        <label className="label">Descripción:</label>
                                        <input
                                            className="input"
                                            type="text"
                                            value={descripcion}
                                            onChange={(e) => setDescripcion(e.target.value)}
                                            placeholder="Elección Nacional 2025"
                                        />
                                    </div>

                                    <div className="field">
                                        <label className="label">Tipo:</label>
                                        <input
                                            className="input"
                                            type="text"
                                            value={tipo}
                                            onChange={(e) => setTipo(e.target.value)}
                                            placeholder="Nacional"
                                        />
                                    </div>

                                    <div className="field">
                                        <label className="label">Fecha:</label>
                                        <input
                                            className="input"
                                            type="date"
                                            value={fecha}
                                            onChange={(e) => setFecha(e.target.value)}
                                        />
                                    </div>

                                    <div className="field has-text-centered">
                                        <button className="button is-primary is-fullwidth" onClick={manejarCargarEleccion}>
                                            Guardar
                                        </button>
                                    </div>

                                    {mensajeEleccion && (
                                        <div className="notification mt-3 is-light has-text-centered">
                                            {mensajeEleccion}
                                        </div>
                                    )}
                                </div>
                            )}
                        </>
                    )}
                </div>
            </div>
        </section>
    );
}