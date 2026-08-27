import api from "./axios";

const CONTACT_URL = "http://localhost:8082/api/contacts";

export const getContacts = () =>
    api.get(CONTACT_URL);

export const getContact = (id) =>
    api.get(`${CONTACT_URL}/${id}`);

export const createContact = (data) =>
    api.post(CONTACT_URL, data);

export const updateContact = (id, data) =>
    api.put(`${CONTACT_URL}/${id}`, data);

export const deleteContact = (id) =>
    api.delete(`${CONTACT_URL}/${id}`);

export const searchContacts = (name) =>
    api.get(`${CONTACT_URL}/search`, {
        params: { name }
    });

export const exportContacts = () =>
    api.get(`${CONTACT_URL}/export`, {
        responseType: "blob"
    });