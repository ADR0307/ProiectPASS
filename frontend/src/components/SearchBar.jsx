import { TextField, Box, Button } from "@mui/material";

export default function SearchBar({
    value,
    onChange,
    onSearch,
    onClear
}) {

    return (
        <Box
            sx={{
                display: "flex",
                gap: 1,
                mb: 3
            }}
        >

            <TextField
                fullWidth
                label="Caută după nume"
                value={value}
                onChange={(e) => onChange(e.target.value)}
                onKeyDown={(e) => {
                    if (e.key === "Enter") {
                        onSearch();
                    }
                }}
            />

            <Button
                variant="contained"
                onClick={onSearch}
            >
                Search
            </Button>

            <Button
                variant="outlined"
                onClick={onClear}
            >
                Clear
            </Button>

        </Box>
    );
}

