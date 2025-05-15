import {Button, TextField} from "@mui/material";
import React, {useState} from "react";
import {useUserAuthentication} from "../../../hooks/useUserAuthentication.js";


const initialFormData = {
    username: "",
    password: "",
}


export const LoginPage = () => {

   const [formData, setFromData] = useState(initialFormData);
   const dologin = useUserAuthentication()


   function handleChange(event) {
       const {name, value} = event.target;

       setFromData({...formData, [name]: value});
   }

   function handleLoginSubmit() {

       dologin(formData)


   }

    return (
    <>
        <TextField
            margin="dense"
            label="UserName"
            name="username"
            value={formData.username}
            onChange={handleChange}
            fullWidth/>

        <TextField
            margin="dense"
            label="Password"
            name="password"
            value={formData.password}
            onChange={handleChange}
            fullWidth/>
            <Button onClick={handleLoginSubmit} variant="contained" color="primary">Login</Button>

    </>
    )



}