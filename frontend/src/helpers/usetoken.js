import { useState } from "react";
import { jwtDecode } from "jwt-decode";

export default function useToken() {

    const getToken = () => {
        const token = sessionStorage.getItem("token");
        if (!token) return null;
        
        try {
            
            const decoded = jwtDecode(token);
            const currentTime = Math.floor(Date.now() / 1000);
            
            if (decoded.exp < currentTime) {
                sessionStorage.removeItem("token");
                return null;
            }
            
            return token;

        } catch (error) {
            sessionStorage.removeItem("token");
            return null;
        }
    };

    const [token, setTokenState] = useState(() => getToken());

    const saveToken = (jwtString) => {
        sessionStorage.setItem("token", jwtString);
        setTokenState(jwtString);
    };

    const removeToken = () => {
        sessionStorage.removeItem("token");
        setTokenState(null);
    };

    return {
        token,
        setToken: saveToken,
        removeToken
    };
}
