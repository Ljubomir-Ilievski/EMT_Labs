import axios from "axios";

const axiosInstance = axios.create({
    baseURL: "http://localhost:8080/api",
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