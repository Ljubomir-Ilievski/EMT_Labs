import axiosInstance from "../axios/axios.js";

const countryRepository = {
    findAll: async () => {
        return await axiosInstance.get("/manufacturers");
    },
    findById: async (id) => {
        return await axiosInstance.get(`/manufacturers/${id}`);
    },
};

export default countryRepository;