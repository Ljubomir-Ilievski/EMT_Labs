import axios from "axios";
import {backendUrl} from "../constants/constants.js";

console.log('backendUrl!!!!', backendUrl);

const axiosInstance = axios.create({
    baseURL: backendUrl,
    headers: {
        "Content-Type": "application/json",
    },
});

// Interceptor to add Authorization header
axiosInstance.interceptors.request.use(
    (config) => {
        const userData = JSON.parse(localStorage.getItem("userAuthentication"));
        if (userData && userData.token) {
            config.headers["Authorization"] = `Bearer ${userData.token}`;
        }
        return config;
    },
    (error) => Promise.reject(error)
);

export default axiosInstance;