import React from 'react';
import BookCard from "../BookCard/BookCard.jsx";
import {Grid} from "@mui/material";

const ProductsGrid = ({products, onEdit, onDelete}) => {
    return (
        <Grid container spacing={{xs: 2, md: 3}}>
            {products.map((product) => (
                <Grid key={product.id} size={{xs: 12, sm: 6, md: 4, lg: 3}}>
                    <BookCard product={product} onEdit={onEdit} onDelete={onDelete}/>
                </Grid>
            ))}
        </Grid>
    );
};

export default ProductsGrid;