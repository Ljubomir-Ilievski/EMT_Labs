import axiosInstance from "../axios/axios.js";

const booksRepository = {
    findAll: async () => {
        return await axiosInstance.get("/books");
    },
    findPage: async (page, size) => {
        return await axiosInstance.get("/books/pagination", {params: {page, size}});
    },
    findById: async (id) => {
        return await axiosInstance.get(`/books/${id}`);
    },
    add: async (data) => {
      return await axiosInstance.post("/books/add", data);
    },
    edit: async (id, data) => {
     return await axiosInstance.put(`/books/update/${id}`, data);
    },
    delete: async (id) => {
        return await axiosInstance.delete(`/books/delete/${id}`);
    },
};

export default booksRepository;