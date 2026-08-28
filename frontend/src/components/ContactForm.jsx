import { useEffect, useState } from "react";
import {
    Dialog,
    DialogTitle,
    DialogContent,
    DialogActions,
    TextField,
    Button,
    Avatar,
    Box
} from "@mui/material";

const emptyForm = {
    firstName: "",
    lastName: "",
    phone: "",
    email: "",
    company: "",
    address: "",
    picture: ""
};

export default function ContactForm({
                                        open,
                                        contact,
                                        onClose,
                                        onSave
                                    }) {

    const [form, setForm] = useState(emptyForm);

    useEffect(() => {

        if (contact) {
            setForm({
                firstName: contact.firstName || "",
                lastName: contact.lastName || "",
                phone: contact.phone || "",
                email: contact.email || "",
                company: contact.company || "",
                address: contact.address || "",
                picture: contact.picture || ""
            });
        } else {
            setForm(emptyForm);
        }

    }, [contact, open]);

    const handleChange = (e) => {

        const { name, value } = e.target;

        setForm(prev => ({
            ...prev,
            [name]: value
        }));
    };

    const handleSubmit = () => {
        onSave(form);
    };

    return (
        <Dialog
            open={open}
            onClose={onClose}
            fullWidth
            maxWidth="sm"
        >

            <DialogTitle>
                {contact ? "Edit Contact" : "Add Contact"}
            </DialogTitle>

            <DialogContent>

                <Box display="flex" justifyContent="center" my={2}>
                    <Avatar
                        src={form.picture || ""}
                        sx={{ width: 90, height: 90 }}
                    />
                </Box>

                <TextField
                    fullWidth
                    label="Picture URL"
                    name="picture"
                    value={form.picture}
                    onChange={handleChange}
                    margin="normal"
                />

                <TextField
                    fullWidth
                    margin="normal"
                    label="First Name"
                    name="firstName"
                    value={form.firstName}
                    onChange={handleChange}
                />

                <TextField
                    fullWidth
                    margin="normal"
                    label="Last Name"
                    name="lastName"
                    value={form.lastName}
                    onChange={handleChange}
                />

                <TextField
                    fullWidth
                    margin="normal"
                    label="Phone"
                    name="phone"
                    value={form.phone}
                    onChange={handleChange}
                />

                <TextField
                    fullWidth
                    margin="normal"
                    label="Email"
                    name="email"
                    value={form.email}
                    onChange={handleChange}
                />

                <TextField
                    fullWidth
                    margin="normal"
                    label="Company"
                    name="company"
                    value={form.company}
                    onChange={handleChange}
                />

                <TextField
                    fullWidth
                    margin="normal"
                    label="Address"
                    name="address"
                    value={form.address}
                    onChange={handleChange}
                />

            </DialogContent>

            <DialogActions>

                <Button onClick={onClose}>
                    Cancel
                </Button>

                <Button
                    variant="contained"
                    onClick={handleSubmit}
                >
                    Save
                </Button>

            </DialogActions>

        </Dialog>
    );
}