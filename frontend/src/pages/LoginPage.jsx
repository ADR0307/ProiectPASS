import { useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import { login } from "../api/authApi";

import {
    Box,
    Paper,
    Typography,
    TextField,
    Button,
    Alert
} from "@mui/material";

export default function LoginPage() {

    const navigate = useNavigate();

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("");

    const handleLogin = async () => {

        try {

            const response = await login({
                email,
                password
            });

            localStorage.setItem("token", response.data.token);

            navigate("/contacts");

        } catch {

            setError("Email sau parolă incorecte.");

        }

    };

    return (
        <Box
            sx={{
                display: "flex",
                justifyContent: "center",
                alignItems: "center",
                minHeight: "100vh",
                bgcolor: "#f5f5f5"
            }}
        >
            <Paper sx={{ p: 4, width: 400 }} elevation={4}>

                <Typography variant="h4" mb={3}>
                    AddressBook
                </Typography>

                {error && (
                    <Alert severity="error" sx={{ mb: 2 }}>
                        {error}
                    </Alert>
                )}

                <TextField
                    fullWidth
                    label="Email"
                    margin="normal"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                />

                <TextField
                    fullWidth
                    type="password"
                    label="Parolă"
                    margin="normal"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                />

                <Button
                    fullWidth
                    variant="contained"
                    sx={{ mt: 2 }}
                    onClick={handleLogin}
                >
                    Login
                </Button>

                <Typography mt={2} textAlign="center">

                    Nu ai cont?{" "}

                    <Link to="/register">
                        Înregistrează-te
                    </Link>

                </Typography>

            </Paper>
        </Box>
    );
}