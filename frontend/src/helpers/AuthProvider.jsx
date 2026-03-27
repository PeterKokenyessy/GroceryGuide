import { useState } from "react";
import { AuthContext } from "./AuthContext";
import { jwtDecode } from "jwt-decode";

export function AuthProvider({ children }) {
  const [auth, setAuth] = useState(() => {
    const stored = sessionStorage.getItem("auth");

    if (stored?.token) return null;

    try {
      
      const decoded = jwtDecode(stored.token);
      const currentTime = Math.floor(Date.now() / 1000);
      
      if (decoded.exp < currentTime) {
        sessionStorage.removeItem("auth");
        return null;
      }
      

      return JSON.parse(stored);

    } catch (error) {
      sessionStorage.removeItem("auth");
      return null;
    }
  });

  const login = (authData) => {
    sessionStorage.setItem("auth", JSON.stringify(authData));
    setAuth(authData);
  };

  const logout = () => {
    sessionStorage.removeItem("auth");
    setAuth(null);
  };
    const updateToken = (newAccessToken) => {
        const updated = { ...auth, token: newAccessToken };
        sessionStorage.setItem("auth", JSON.stringify(updated));
        setAuth(updated);
    };

  return (
    <AuthContext.Provider
      value={{
        token: auth?.token || null,
        userName: auth?.userName || null,
        isAuthenticated: !!auth?.token,
          login,
          logout,
          updateToken
      }}
    >
      {children}
    </AuthContext.Provider>
  );
}