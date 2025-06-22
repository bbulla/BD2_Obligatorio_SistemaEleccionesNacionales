import React, { createContext, useContext, useState } from "react";

// Creamos el contexto
const CircuitoContext = createContext();

// Hook para usarlo
export const useCircuito = () => useContext(CircuitoContext);

// Componente proveedor
export const CircuitoProvider = ({ children }) => {
    const [circuito, setCircuito] = useState(null);
    const [eleccion, setEleccion] = useState(null);

    return (
        <CircuitoContext.Provider value={{ circuito, setCircuito, eleccion, setEleccion }}>
            {children}
        </CircuitoContext.Provider>
    );
};