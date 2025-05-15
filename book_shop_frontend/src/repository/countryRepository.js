import axiosInstance from "../axios/axios.js";

const manufacturerRepository = {
    findAll: async () => {
        return await axiosInstance.get("/manufacturers");
    },
    findById: async (id) => {
        return await axiosInstance.get(`/manufacturers/${id}`);
    },
};

export default manufacturerRepository;