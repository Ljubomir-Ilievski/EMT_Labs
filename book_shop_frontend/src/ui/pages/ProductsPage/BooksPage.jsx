import React, {useState} from 'react';
import {Box, Button, CircularProgress, Pagination} from "@mui/material";
import BooksGrid from "../../components/books/BooksGrid/BooksGrid.jsx";
import useBooks from "../../../hooks/useBooks.js";
import "./BooksPage.css";
import AddBookDialog from "../../components/books/AddBookDialog/AddBookDialog.jsx";

const BooksPage = () => {
    const {books, loading, onAdd, onEdit, onDelete} = useBooks();
    const [addProductDialogOpen, setAddProductDialogOpen] = useState(false);
    const [page, setPage] = useState(1);

    function handlePageChange(event, value){
        setPage(value);
    }

    return (
        <>
            <Box className="products-box">
                {loading && (
                    <Box className="progress-box">
                        <CircularProgress/>
                    </Box>
                )}
                {!loading &&
                    <>
                        <Box sx={{display: "flex", justifyContent: "flex-end", mb: 2}}>
                            <Button variant="contained" color="primary" onClick={() => setAddProductDialogOpen(true)}>
                                Add Book
                            </Button>
                        </Box>
                        <Pagination count={3} shape="rounded"
                                    onChange={handlePageChange}/>
                        <br/>
                        <BooksGrid books={books} onEdit={onEdit} onDelete={onDelete}/>

                    </>}
            </Box>
            <AddBookDialog
                open={addProductDialogOpen}
                onClose={() => setAddProductDialogOpen(false)}
                onAdd={onAdd}
            />
        </>
    );
};

export default BooksPage;