import React, {useState} from 'react';
import InfoIcon from '@mui/icons-material/Info';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import {Box, Button, Card, CardActions, CardContent, Typography} from "@mui/material";
import EditProductDialog from "../EditProductDialog/EditProductDialog.jsx";
import DeleteProductDialog from "../DeleteProductDialog/DeleteProductDialog.jsx";
import {useNavigate} from "react-router";

const ProductCard = ({product, onEdit, onDelete}) => {
    const navigate = useNavigate();
    const [editProductDialogOpen, setEditProductDialogOpen] = useState(false);
    const [deleteProductDialogOpen, setDeleteProductDialogOpen] = useState(false);

    return (
        <>
            <Card sx={{boxShadow: 3, borderRadius: 2, p: 1}}>
                <CardContent>
                    <Typography variant="h5">{product.name}</Typography>
                    <Typography variant="subtitle2">
                        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ab aperiam assumenda blanditiis cum
                        ducimus enim modi natus odit quibusdam veritatis.
                    </Typography>
                    <Typography variant="body1" fontWeight="bold"
                                sx={{textAlign: "right", fontSize: "1.25rem"}}>${product.price}</Typography>
                    <Typography variant="body2" sx={{textAlign: "right"}}>{product.quantity} piece(s)
                        available</Typography>
                </CardContent>
                <CardActions sx={{justifyContent: "space-between"}}>
                    <Button
                        size="small"
                        color="info"
                        startIcon={<InfoIcon/>}
                        onClick={() => navigate(`/products/${product.id}`)}
                    >
                        Info
                    </Button>
                    <Box>
                        <Button
                            size="small"
                            color="warning"
                            startIcon={<EditIcon/>}
                            sx={{mr: "0.25rem"}}
                            onClick={() => setEditProductDialogOpen(true)}
                        >
                            Edit
                        </Button>
                        <Button
                            size="small"
                            color="error"
                            startIcon={<DeleteIcon/>}
                            onClick={() => setDeleteProductDialogOpen(true)}
                        >
                            Delete
                        </Button>
                    </Box>
                </CardActions>
            </Card>
            <EditProductDialog
                open={editProductDialogOpen}
                onClose={() => setEditProductDialogOpen(false)}
                product={product}
                onEdit={onEdit}
            />
            <DeleteProductDialog
                open={deleteProductDialogOpen}
                onClose={() => setDeleteProductDialogOpen(false)}
                product={product}
                onDelete={onDelete}
            />
        </>
    );
};

export default ProductCard;