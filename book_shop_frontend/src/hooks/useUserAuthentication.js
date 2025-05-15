import {userAuthenticationRepository} from "../repository/userAuthenticationRepository.js";


export const useUserAuthentication= () => {

     const doLoginIn = (loginData) => {
        userAuthenticationRepository.login(loginData)
            .then(response => {localStorage.setItem("userAuthentication", JSON.stringify(response.data))})
            .catch(err => console.log(err));
    }

    return doLoginIn;


}