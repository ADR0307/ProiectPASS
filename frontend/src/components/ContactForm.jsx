import { useEffect, useState } from "react";
import {
    Dialog,
    DialogTitle,
    DialogContent,
    DialogActions,
    TextField,
    Button
} from "@mui/material";

const emptyForm = {
    firstName: "",
    lastName: "",
    phone: "",
    email: "",
    company: ""
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
                company: contact.company || ""
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

