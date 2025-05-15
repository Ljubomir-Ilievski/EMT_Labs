import axiosInstance from "../axios/axios.js";

const productRepository = {
    findAll: async () => {
        return await axiosInstance.get("/products");
    },
    findById: async (id) => {
        return await axiosInstance.get(`/products/${id}`);
    },
    add: async (data) => {
      return await axiosInstance.post("/products/add", data);
    },
    edit: async (id, data) => {
     return await axiosInstance.put(`/products/edit/${id}`, data);
    },
    delete: async (id) => {
        return await axiosInstance.delete(`/products/delete/${id}`);
    },
};

export default productRepository;