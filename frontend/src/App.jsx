import { Routes, Route, Navigate } from "react-router-dom";

import LoginPage from "./pages/LoginPage";
import RegisterPage from "./pages/RegisterPage";
import ContactsPage from "./pages/ContactsPage";

export default function App() {

  const token = localStorage.getItem("token");

  return (
      <Routes>

        <Route path="/login" element={<LoginPage />} />

        <Route path="/register" element={<RegisterPage />} />

        <Route
            path="/contacts"
            element={
              token ? <ContactsPage /> : <Navigate to="/login" />
            }
        />

        <Route
            path="*"
            element={<Navigate to="/contacts" />}
        />

      </Routes>
  );
}