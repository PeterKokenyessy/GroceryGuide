import { useLocation, Navigate } from "react-router-dom";

function RedirectAfterLogin() {
  const location = useLocation();

  const fromLocation = location.state?.from;

  const to = fromLocation
    ? fromLocation.pathname + fromLocation.search
    : "/";

  return <Navigate to={to} replace />;
}

export default RedirectAfterLogin