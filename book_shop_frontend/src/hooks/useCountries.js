import {useEffect, useState} from "react";
import manufacturerRepository from "../repository/manufacturerRepository.js";

const useManufacturers = () => {
    const [manufacturers, setManufacturers] = useState([]);

    useEffect(() => {
        manufacturerRepository
            .findAll()
            .then((response) => {
                setManufacturers(response.data);
            })
            .catch((error) => console.log(error));
    }, []);

    return manufacturers;
};

export default useManufacturers;