import { useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import { register } from "../api/authApi";

import {
    Box,
    Paper,
    Typography,
    TextField,
    Button,
    Alert
} from "@mui/material";

export default function RegisterPage() {

    const navigate = useNavigate();

    const [form, setForm] = useState({
        email: "",
        password: ""
    });

    const [success, setSuccess] = useState(false);

    const [error, setError] = useState("");


    const handleChange = (e) => {

        setForm({
            ...form,
            [e.target.name]: e.target.value
        });

    };


    const handleRegister = async () => {

        try {

            setError("");

            await register(form);

            setSuccess(true);

            setTimeout(() => {
                navigate("/login");
            }, 1500);

        } catch (error) {

            console.error(error);

            setError("Utilizatorul există deja.");

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

            <Paper
                sx={{
                    p: 4,
                    width: 400
                }}
                elevation={4}
            >

                <Typography
                    variant="h4"
                    mb={3}
                >
                    Register
                </Typography>


                {success && (
                    <Alert
                        severity="success"
                        sx={{ mb: 2 }}
                    >
                        Cont creat! Vei fi redirecționat către Login.
                    </Alert>
                )}


                {error && (
                    <Alert
                        severity="error"
                        sx={{ mb: 2 }}
                    >
                        {error}
                    </Alert>
                )}


                <TextField
                    fullWidth
                    name="email"
                    label="Email"
                    type="email"
                    margin="normal"
                    value={form.email}
                    onChange={handleChange}
                />


                <TextField
                    fullWidth
                    name="password"
                    type="password"
                    label="Parolă"
                    margin="normal"
                    value={form.password}
                    onChange={handleChange}
                />


                <Button
                    fullWidth
                    variant="contained"
                    sx={{ mt: 2 }}
                    onClick={handleRegister}
                >
                    Register
                </Button>


                <Typography
                    mt={2}
                    sx={{
                        textAlign: "center"
                    }}
                >
                    Ai deja cont?{" "}

                    <Link to="/login">
                        Login
                    </Link>

                </Typography>

            </Paper>

        </Box>
    );
}