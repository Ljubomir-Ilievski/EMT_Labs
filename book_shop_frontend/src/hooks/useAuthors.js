import {useEffect, useState} from "react";
import authorRepositroy from "../repository/authorRepositroy.js";

const useAuthors = () => {
    const [authors, setAuthors] = useState([]);

    useEffect(() => {
        authorRepositroy
            .findAll()
            .then((response) => {
                setAuthors(response.data);
            })
            .catch((error) => console.log(error));
    }, []);

    return authors;
};

export default useAuthors;