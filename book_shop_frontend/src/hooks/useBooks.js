import {useCallback, useEffect, useState} from "react";
import productRepository from "../repository/productRepository.js";

const initialState = {
    "products": [],
    "loading": true,
};

const useProducts = () => {
    const [state, setState] = useState(initialState);

    const fetchProducts = useCallback(() => {
        productRepository
            .findAll()
            .then((response) => {
                setState({
                    "products": response.data,
                    "loading": false,
                });
            })
            .catch((error) => console.log(error));
    }, []);

    const onAdd = useCallback((data) => {
        productRepository
            .add(data)
            .then(() => {
                console.log("Successfully added a new product.");
                fetchProducts();
            })
            .catch((error) => console.log(error));
    }, [fetchProducts]);

    const onEdit = useCallback((id, data) => {
        productRepository
            .edit(id, data)
            .then(() => {
                console.log(`Successfully edited the product with ID ${id}.`);
                fetchProducts();
            })
            .catch((error) => console.log(error));
    }, [fetchProducts]);

    const onDelete = useCallback((id) => {
        productRepository
            .delete(id)
            .then(() => {
                console.log(`Successfully deleted the product with ID ${id}.`);
                fetchProducts();
            })
            .catch((error) => console.log(error));
    }, [fetchProducts]);

    useEffect(() => {
        fetchProducts();
    }, [fetchProducts]);

    return {...state, onAdd: onAdd, onEdit: onEdit, onDelete: onDelete};
};

export default useProducts;