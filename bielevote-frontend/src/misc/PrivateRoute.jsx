import PropTypes from "prop-types";
import { Navigate } from "react-router-dom";
import { useAuth } from "./AuthContext";

export default function PrivateRoute({ children }) {
  const { userIsAuthenticated } = useAuth();
  return userIsAuthenticated() ? children : <Navigate to="/" />;
}

PrivateRoute.propTypes = {
  children: PropTypes.any,
};
