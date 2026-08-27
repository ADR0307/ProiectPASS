import { useEffect, useState } from "react";

import {
    Container,
    Typography,
    Button,
    Box,
    Alert,
    CircularProgress
} from "@mui/material";

import Navbar from "../components/Navbar";
import ContactCard from "../components/ContactCard";
import ContactForm from "../components/ContactForm";
import SearchBar from "../components/SearchBar";

import {
    getContacts,
    createContact,
    updateContact,
    deleteContact,
    searchContacts,
    exportContacts
} from "../api/contactApi";

export default function ContactsPage() {

    const [contacts, setContacts] = useState([]);

    const [loading, setLoading] = useState(true);

    const [error, setError] = useState("");

    const [search, setSearch] = useState("");

    const [formOpen, setFormOpen] = useState(false);

    const [selectedContact, setSelectedContact] = useState(null);


    const loadContacts = async () => {

        try {

            setLoading(true);
            setError("");

            const response = await getContacts();

            setContacts(response.data);

        } catch (error) {

            console.error(error);

            setError("Nu am putut încărca contactele.");

        } finally {

            setLoading(false);

        }
    };


    useEffect(() => {
        loadContacts();
    }, []);


    const handleSearch = async () => {

        if (!search.trim()) {
            await loadContacts();
            return;
        }

        try {

            setLoading(true);
            setError("");

            const response = await searchContacts(search);

            setContacts(response.data);

        } catch (error) {

            console.error(error);

            setError("Căutarea a eșuat.");

        } finally {

            setLoading(false);

        }
    };


    const handleClearSearch = async () => {

        setSearch("");

        await loadContacts();
    };


    const handleAdd = () => {

        setSelectedContact(null);

        setFormOpen(true);
    };


    const handleEdit = (contact) => {

        setSelectedContact(contact);

        setFormOpen(true);
    };


    const handleSave = async (data) => {

        try {

            setError("");

            if (selectedContact) {

                await updateContact(
                    selectedContact.id,
                    data
                );

            } else {

                await createContact(data);

            }

            setFormOpen(false);

            setSelectedContact(null);

            await loadContacts();

        } catch (error) {

            console.error(error);

            setError("Nu am putut salva contactul.");

        }
    };


    const handleDelete = async (id) => {

        const confirmed = window.confirm(
            "Sigur vrei să ștergi acest contact?"
        );

        if (!confirmed) {
            return;
        }

        try {

            setError("");

            await deleteContact(id);

            await loadContacts();

        } catch (error) {

            console.error(error);

            setError("Nu am putut șterge contactul.");

        }
    };


    const handleExport = async () => {

        try {

            setError("");

            const response = await exportContacts();

            const url = window.URL.createObjectURL(
                new Blob(
                    [response.data],
                    { type: "text/csv" }
                )
            );

            const link = document.createElement("a");

            link.href = url;

            link.setAttribute(
                "download",
                "contacts.csv"
            );

            document.body.appendChild(link);

            link.click();

            link.remove();

            window.URL.revokeObjectURL(url);

        } catch (error) {

            console.error(error);

            setError("Exportul CSV a eșuat.");

        }
    };


    return (
        <>
            <Navbar />

            <Container
                maxWidth="lg"
                sx={{
                    mt: 5,
                    mb: 5
                }}
            >

                <Box
                    sx={{
                        display: "flex",
                        justifyContent: "space-between",
                        alignItems: "center",
                        mb: 4
                    }}
                >

                    <Box>

                        <Typography
                            variant="h4"
                            sx={{
                                fontWeight: 600
                            }}
                        >
                            Contacts
                        </Typography>

                        <Typography
                            variant="body1"
                            color="text.secondary"
                            sx={{ mt: 1 }}
                        >
                            Manage your contacts
                        </Typography>

                    </Box>


                    <Box
                        sx={{
                            display: "flex",
                            gap: 1
                        }}
                    >

                        <Button
                            variant="outlined"
                            onClick={handleExport}
                        >
                            Export CSV
                        </Button>

                        <Button
                            variant="contained"
                            onClick={handleAdd}
                        >
                            Add Contact
                        </Button>

                    </Box>

                </Box>


                {error && (
                    <Alert
                        severity="error"
                        sx={{ mb: 3 }}
                    >
                        {error}
                    </Alert>
                )}


                <SearchBar
                    value={search}
                    onChange={setSearch}
                    onSearch={handleSearch}
                    onClear={handleClearSearch}
                />


                {loading ? (

                    <Box
                        sx={{
                            display: "flex",
                            justifyContent: "center",
                            mt: 6
                        }}
                    >
                        <CircularProgress />
                    </Box>

                ) : contacts.length === 0 ? (

                    <Typography
                        color="text.secondary"
                        sx={{
                            mt: 6,
                            textAlign: "center"
                        }}
                    >
                        Nu există contacte.
                    </Typography>

                ) : (

                    <Box
                        sx={{
                            display: "grid",
                            gridTemplateColumns: {
                                xs: "1fr",
                                sm: "repeat(2, 1fr)",
                                md: "repeat(3, 1fr)"
                            },
                            gap: 3,
                            mt: 3
                        }}
                    >

                        {contacts.map((contact) => (

                            <ContactCard
                                key={contact.id}
                                contact={contact}
                                onEdit={handleEdit}
                                onDelete={handleDelete}
                            />

                        ))}

                    </Box>

                )}


                <ContactForm
                    open={formOpen}
                    contact={selectedContact}
                    onClose={() => {
                        setFormOpen(false);
                        setSelectedContact(null);
                    }}
                    onSave={handleSave}
                />

            </Container>
        </>
    );
}