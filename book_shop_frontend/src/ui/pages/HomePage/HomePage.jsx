import React from 'react';
import {Box, Container, Typography} from "@mui/material";

const HomePage = () => {
    return (
        <Box sx={{m:"2rem", p:"2rem"}}>
            <Container maxWidth="xl" sx={{mt:3, py: 3}}>
                <Typography variant="h4" gutterBottom>
                    Welcome to the library! 📚📚📚
                </Typography>
                <Typography variant="body1" sx={{ mb: 4 }}>
                    This is the home page.
                </Typography>
            </Container>
        </Box>
    );
};

export default HomePage;