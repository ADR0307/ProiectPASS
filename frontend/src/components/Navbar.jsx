import { AppBar, Toolbar, Typography, Button, Box } from "@mui/material";
import { useNavigate } from "react-router-dom";

export default function Navbar() {

    const navigate = useNavigate();

    const logout = () => {
        localStorage.removeItem("token");
        navigate("/login");
    };

    return (
        <AppBar position="static">
            <Toolbar>

                <Typography
                    variant="h6"
                    sx={{ flexGrow: 1, cursor: "pointer" }}
                    onClick={() => navigate("/contacts")}
                >
                    AddressBook
                </Typography>

                <Box>
                    <Button
                        color="inherit"
                        onClick={logout}
                    >
                        Logout
                    </Button>
                </Box>

            </Toolbar>
        </AppBar>
    );
}

