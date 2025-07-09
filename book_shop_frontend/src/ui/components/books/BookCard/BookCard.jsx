import React, {useState} from 'react';
import InfoIcon from '@mui/icons-material/Info';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import {Box, Button, Card, CardActions, CardContent, Typography} from "@mui/material";
import EditBookDialog from "../EditBookDialog/EditBookDialog.jsx"
import DeleteBookDialog from "../DeleteBookDialog/DeleteBookDialog.jsx";
import {useNavigate} from "react-router";

const bookCard = ({book, onEdit, onDelete}) => {
    const navigate = useNavigate();
    const [editBookDialogOpen, seteditBookDialogOpen] = useState(false);
    const [deleteBookDialogOpen, setdeleteBookDialogOpen] = useState(false);

    return (
        <>
            <Card sx={{boxShadow: 3, borderRadius: 2, p: 1}}>
                <CardContent>
                    <Typography variant="h5">{book.name}</Typography>
                    <Typography variant="h5">Author: {book.author}</Typography>
                    <Typography variant="subtitle2">
                        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ab aperiam assumenda blanditiis cum
                        ducimus enim modi natus odit quibusdam veritatis.
                    </Typography>
                    <Typography variant="body2" sx={{textAlign: "right"}}>Category: {book.category}</Typography>
                    <Typography variant="body2" sx={{textAlign: "right"}}>{book.availableCopies} piece(s) available</Typography>
                </CardContent>
                <CardActions>
                    <Box>
                        <Button
                            size="small"
                            color="warning"
                            startIcon={<EditIcon/>}
                            sx={{mr: "0.25rem"}}
                            onClick={() => seteditBookDialogOpen(true)}
                        >
                            Edit
                        </Button>
                        <Button
                            size="small"
                            color="error"
                            startIcon={<DeleteIcon/>}
                            onClick={() => setdeleteBookDialogOpen(true)}
                        >
                            Delete
                        </Button>
                    </Box>
                </CardActions>
            </Card>
            <EditBookDialog
                open={editBookDialogOpen}
                onClose={() => seteditBookDialogOpen(false)}
                book={book}
                onEdit={onEdit}
            />
            <DeleteBookDialog
                open={deleteBookDialogOpen}
                onClose={() => setdeleteBookDialogOpen(false)}
                book={book}
                onDelete={onDelete}
            />
        </>
    );
};

export default bookCard;