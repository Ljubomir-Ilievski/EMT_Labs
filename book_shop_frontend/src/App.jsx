import React from 'react';
import ProductsPage from "./ui/pages/ProductsPage/ProductsPage.jsx";
import {BrowserRouter, Routes, Route} from "react-router";
import Layout from "./ui/components/layout/Layout/Layout.jsx";
import HomePage from "./ui/pages/HomePage/HomePage.jsx";
import ProductDetails from "./ui/components/products/ProductDetails/ProductDetails.jsx";

const App = () => {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Layout/>}>
                    <Route index element={<HomePage/>}/>
                    <Route path="products" element={<ProductsPage/>}/>
                    <Route path="products/:id" element={<ProductDetails/>}/>
                </Route>
            </Routes>
        </BrowserRouter>
    );
};

export default App;