import { Routes, Route } from "react-router-dom";
import SeleccionarCircuito from "./pages/SeleccionarCircuito";
import MenuPrincipal from "./pages/MenuPrincipal";
import VerificarVotante from "./pages/VerificarVotante";
import Votar from "./pages/Votar";
import Admin from "./pages/Admin";



function App() {
    return (
        <Routes>
            <Route path="/" element={<SeleccionarCircuito />} />
            <Route path="/menu" element={<MenuPrincipal />} />
            <Route path="/verificar" element={<VerificarVotante />} />
            <Route path="/votar" element={<Votar />} />
            <Route path="/admin" element={<Admin />} />
        </Routes>
    );
}

export default App;
