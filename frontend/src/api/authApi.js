import api from "./axios";

const USER_URL = "http://localhost:8081/api/auth";

export const login = (data) =>
    api.post(`${USER_URL}/login`, data);

export const register = (data) =>
    api.post(`${USER_URL}/register`, data);