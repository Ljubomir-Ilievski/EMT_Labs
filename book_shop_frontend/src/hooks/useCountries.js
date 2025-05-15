import {useEffect, useState} from "react";
import countryRepository from "../repository/countryRepository.js";

const useCountries = () => {
    const [manufacturers, setManufacturers] = useState([]);

    useEffect(() => {
        countryRepository
            .findAll()
            .then((response) => {
                setManufacturers(response.data);
            })
            .catch((error) => console.log(error));
    }, []);

    return manufacturers;
};

export default useCountries;