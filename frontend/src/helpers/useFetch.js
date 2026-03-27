import { useCallback, useEffect, useState, useContext } from "react";
import { AuthContext } from "./AuthContext.jsx"

export function useFetch(url, options = {}, immediate = true) {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(immediate); 
  const [isFetching, setIsFetching] = useState(false); 
  const [error, setError] = useState(null);

  const { token, updateToken, logout } = useContext(AuthContext);

  //felteteles token
  const fetchData = useCallback(async (overrideOptions = {}) => {
    if (data) {
      setIsFetching(true);
    } else {
      setLoading(true);
    }

    setError(null);

    try {
      let response = await fetch(url, {
        ...options,
        ...overrideOptions,
        headers: {
          ...options.headers,
          ...overrideOptions.headers,
          Authorization: `Bearer ${token}`,
        },
      });

      if (response.status === 401) {
        const refreshResponse = await fetch("http://localhost:8080/api/auth/refresh", {
          method: "POST",
          credentials: "include",
        });

        if (refreshResponse.ok) {
          const refreshData = await refreshResponse.json();
          updateToken(refreshData.accessToken);

          response = await fetch(url, {
            ...options,
            ...overrideOptions,
            headers: {
              ...options.headers,
              ...overrideOptions.headers,
              Authorization: `Bearer ${refreshData.accessToken}`,
            },
          });
        } else {
          logout();
          return;
        }
      }

      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }

      const result =
        response.status === 204 ? null : await response.json();

      setData(result);

    } catch (err) {
      if (err.name !== "AbortError") {
        setError(err);
      }
    } finally {
      setLoading(false);
      setIsFetching(false);
    }

  }, [url, token]); 

  useEffect(() => {
    if (!immediate) return;
    fetchData();
  }, [fetchData, immediate]);

  return { data, loading, isFetching, error, refetch: fetchData };
}