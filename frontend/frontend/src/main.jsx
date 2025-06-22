import React from "react";
import ReactDOM from "react-dom/client";
import App from "./App.jsx";
import { BrowserRouter } from "react-router-dom";
import { CircuitoProvider } from "./context/CircuitoContext.jsx";
import "bulma/css/bulma.min.css";


ReactDOM.createRoot(document.getElementById("root")).render(
    <React.StrictMode>
        <BrowserRouter>
            <CircuitoProvider>
                <App />
            </CircuitoProvider>
        </BrowserRouter>
    </React.StrictMode>
);