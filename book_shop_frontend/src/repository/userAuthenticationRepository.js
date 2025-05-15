import axiosInstance from "../axios/axios.js";


export const userAuthenticationRepository = {

    login: async (loginData) => {
        return await axiosInstance.post("/user/login", loginData);
    }

}