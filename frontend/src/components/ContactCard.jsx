import {
    Card,
    CardContent,
    Typography,
    Button,
    Box,
    Avatar
} from "@mui/material";

export default function ContactCard({
                                        contact,
                                        onEdit,
                                        onDelete
                                    }) {

    return (
        <Card sx={{ mb: 2 }}>

            <CardContent>

                <Box display="flex" alignItems="center" gap={2}>

                    <Avatar
                        src={contact.picture || ""}
                        alt={`${contact.firstName} ${contact.lastName}`}
                        sx={{ width: 56, height: 56 }}
                    />

                    <Box>
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

                        <Typography variant="body2">
                            📍 {contact.address}
                        </Typography>
                    </Box>

                </Box>

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