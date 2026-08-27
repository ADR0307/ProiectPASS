import {
    Card,
    CardContent,
    Typography,
    Button,
    Box
} from "@mui/material";

export default function ContactCard({
    contact,
    onEdit,
    onDelete
}) {

    return (
        <Card sx={{ mb: 2 }}>

            <CardContent>

                <Typography variant="h6">
                    {contact.firstName} {contact.lastName}
                </Typography>

                <Typography color="text.secondary">
                    📞 {contact.phone}
                </Typography>

                <Typography color="text.secondary">
                    ✉️ {contact.email}
                </Typography>

                <Typography color="text.secondary">
                    🏢 {contact.company}
                </Typography>

                <Box sx={{ mt: 2 }}>

                    <Button
                        variant="outlined"
                        sx={{ mr: 1 }}
                        onClick={() => onEdit(contact)}
                    >
                        Edit
                    </Button>

                    <Button
                        variant="outlined"
                        color="error"
                        onClick={() => onDelete(contact.id)}
                    >
                        Delete
                    </Button>

                </Box>

            </CardContent>

        </Card>
    );
}

