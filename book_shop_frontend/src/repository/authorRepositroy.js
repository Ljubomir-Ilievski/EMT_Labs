import axiosInstance from "../axios/axios.js";

const categoryRepository = {
    findAll: async () => {
        return await axiosInstance.get("/categories");
    },
    findById: async (id) => {
        return await axiosInstance.get(`/categories/${id}`);
    },
};

export default categoryRepository;