import React from 'react';
import BooksPage from "./ui/pages/ProductsPage/BooksPage.jsx";
import {BrowserRouter, Routes, Route} from "react-router";
import Layout from "./ui/components/layout/Layout/Layout.jsx";
import HomePage from "./ui/pages/HomePage/HomePage.jsx";
import {LoginPage} from "./ui/pages/LoginPage/LoginPage.jsx";

const App = () => {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Layout/>}>
                    <Route index element={<HomePage/>}/>
                    <Route path="books" element={<BooksPage/>}/>
                    <Route path="login" element={<LoginPage/>}/>
                    {/*<Route path="products/:id" element={<ProductDetails/>}/>*/}
                </Route>
            </Routes>
        </BrowserRouter>
    );
};

export default App;